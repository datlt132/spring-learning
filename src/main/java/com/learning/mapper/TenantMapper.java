package com.learning.mapper;

import com.learning.dto.DataSourceDTO;
import com.learning.dto.TenantDTO;
import com.learning.entity.TenantEntity;

import java.util.Optional;

public interface TenantMapper {
    default TenantDTO mapToDto(TenantEntity tenant) {
        return TenantDTO.builder()
                .tenantId(tenant.getTenantId())
                .dataSource(DataSourceDTO.builder().schemaName(tenant.getSchemaName()).build())
                .build();
    }

    default TenantEntity mapToEntity(TenantDTO dto) {
        return TenantEntity.builder()
                .tenantId(dto.getTenantId())
                .schemaName(Optional.of(dto)
                        .map(TenantDTO::getDataSource)
                        .map(DataSourceDTO::getSchemaName)
                        .orElse(null))
                .build();
    }
}
