package com.learning.service;

import com.learning.config.tenant.FlywayBuilder;
import com.learning.dto.TenantDTO;
import com.learning.entity.TenantEntity;
import com.learning.mapper.TenantMapper;
import com.learning.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TenantService implements TenantMapper {
    private final TenantRepository tenantRepository;
    private final FlywayBuilder flywayBuilder;

    public TenantDTO findByTenantId(String tenantId) {
        return Optional.ofNullable(tenantId)
                .map(tenantRepository::findByTenantId)
                .map(this::mapToDto)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Unknown tenantId: %s", tenantId))
                );
    }

    public List<TenantDTO> findByAllTenants() {
        return tenantRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public TenantEntity createNewTenant(TenantDTO dto) {
        return Optional.ofNullable(dto)
                .map(this::buildDatabaseSchema)
                .map(this::mapToEntity)
                .map(tenantRepository::save)
                .orElseThrow(() -> new RuntimeException("Cannot add new tenant"));
    }

    private TenantDTO buildDatabaseSchema(TenantDTO dto) {
        flywayBuilder.createTenantFlyway(dto).migrate();
        return dto;
    }
}
