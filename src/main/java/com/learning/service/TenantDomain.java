package com.learning.service;

import com.learning.config.tenant.TenantContext;
import com.learning.dto.TenantDTO;
import com.learning.entity.TenantEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TenantDomain {
    private final TenantService tenantService;

    public boolean setTenantInContext(String tenantId) {
        TenantDTO tenant = tenantService.findByTenantId(tenantId);
        TenantContext.setCurrentTenant(tenant);
        return true;
    }

    public List<TenantDTO> getAllTenants() {
        return tenantService.findByAllTenants();
    }

    public TenantEntity createNewTenant(TenantDTO dto) {
        return tenantService.createNewTenant(dto);
    }
}
