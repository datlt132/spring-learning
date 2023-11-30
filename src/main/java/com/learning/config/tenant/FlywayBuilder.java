package com.learning.config.tenant;

import com.learning.dto.DataSourceDTO;
import com.learning.dto.TenantDTO;
import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;
import java.util.Optional;

@RequiredArgsConstructor
public class FlywayBuilder {
    private static final String DEFAULT_SCHEMA_LOCATION = "db/migration/default";
    private static final String TENANT_SCHEMA_LOCATION = "db/migration/tenants";
    private final DataSource dataSource;

    Flyway createDefaultFlyway() {
        return Flyway.configure()
                .dataSource(dataSource)
                .locations(DEFAULT_SCHEMA_LOCATION)
                .schemas(TenantContext.DEFAULT_TENANT_ID)
                .baselineOnMigrate(true)
                .sqlMigrationPrefix("V")
                .load();
    }

    public Flyway createTenantFlyway(TenantDTO tenant) {
        return Flyway.configure()
                .dataSource(dataSource)
                .locations(TENANT_SCHEMA_LOCATION)
                .schemas(getSchemaName(tenant))
                .baselineOnMigrate(true)
                .sqlMigrationPrefix("V")
                .load();
    }

    private String getSchemaName(TenantDTO tenant) {
        return Optional.ofNullable(tenant)
                .map(TenantDTO::getDataSource)
                .map(DataSourceDTO::getSchemaName)
                .orElseThrow(() -> new RuntimeException("tenant model without schema name"));
    }
}
