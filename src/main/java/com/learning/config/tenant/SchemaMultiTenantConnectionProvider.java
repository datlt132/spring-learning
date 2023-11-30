package com.learning.config.tenant;

import com.learning.dto.DataSourceDTO;
import com.learning.dto.TenantDTO;
import lombok.RequiredArgsConstructor;
import org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl;
import org.hibernate.engine.jdbc.connections.spi.AbstractMultiTenantConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

@Component
@RequiredArgsConstructor
public class SchemaMultiTenantConnectionProvider extends AbstractMultiTenantConnectionProvider {
    private static final String HIBERNATE_PROPERTIES_PATH = "C:/Users/Admin/IdeaProjects/spring-learning/src/main/resources/application.properties";
    private static final String DEFAULT_SCHEMA_NAME = "public";
    private final Map<String, ConnectionProvider> connectionProviderMap = new HashMap<>();

    @Override
    public Connection getConnection(String tenantIdentifier) throws SQLException {
        Connection connection = super.getConnection(tenantIdentifier);
        try (Statement statement = connection.createStatement()) {
            statement.execute(String.format("SET SCHEMA '%s';", getTenantSchema()));
            return connection;
        }
    }

    @Override
    protected ConnectionProvider getAnyConnectionProvider() {
        return getConnectionProvider(TenantContext.DEFAULT_TENANT_ID);
    }

    @Override
    protected ConnectionProvider selectConnectionProvider(String tenantIdentifier) {
        return getConnectionProvider(tenantIdentifier);
    }

    private String getTenantSchema() {
        return Optional
                .ofNullable(TenantContext.getCurrentTenant())
                .map(TenantDTO::getDataSource)
                .map(DataSourceDTO::getSchemaName)
                .orElse(DEFAULT_SCHEMA_NAME);
    }

    private ConnectionProvider getConnectionProvider(String tenantIdentifier) {
        return Optional.ofNullable(tenantIdentifier)
                .map(connectionProviderMap::get)
                .orElseGet(() -> createNewConnectionProvider(tenantIdentifier));
    }

    private ConnectionProvider createNewConnectionProvider(String tenantIdentifier) {
        return Optional.ofNullable(tenantIdentifier)
                .map(this::createConnectionProvider)
                .map(connectionProvider -> {
                    connectionProviderMap.put(tenantIdentifier, connectionProvider);
                    return connectionProvider;
                })
                .orElseThrow(() -> new RuntimeException("Cannot create new connection provider for tenant: " + tenantIdentifier));
    }

    private ConnectionProvider createConnectionProvider(String tenantIdentifier) {
        return Optional.ofNullable(tenantIdentifier)
                .map(this::getHibernatePropertiesForTenantId)
                .map(this::initConnectionProvider)
                .orElse(null);
    }

    private Properties getHibernatePropertiesForTenantId(String tenantId) {
        try {
            Properties properties = new Properties();
            try (InputStream inputStream = new FileInputStream(HIBERNATE_PROPERTIES_PATH)) {
                properties.load(inputStream);
            }
            return properties;
        } catch (IOException e) {
            throw new RuntimeException("Cannot open hibernate properties: " + HIBERNATE_PROPERTIES_PATH);
        }
    }

    private ConnectionProvider initConnectionProvider(Properties hibernateProperties) {
        DriverManagerConnectionProviderImpl connectionProvider = new DriverManagerConnectionProviderImpl();
        HashMap<String, Object> configurations = new HashMap<>();
        for (Map.Entry<Object, Object> entry : hibernateProperties.entrySet()) {
            configurations.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
        }
        connectionProvider.configure(configurations);
        return connectionProvider;
    }
}
