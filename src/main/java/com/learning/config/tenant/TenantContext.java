package com.learning.config.tenant;

import com.learning.dto.TenantDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TenantContext {
    public static final String DEFAULT_TENANT_ID = "public";
    private static final ThreadLocal<TenantDTO> currentTenant = new ThreadLocal<>();

    public static TenantDTO getCurrentTenant() {
        return currentTenant.get();
    }

    public static void setCurrentTenant(TenantDTO tenant) {
        currentTenant.set(tenant);
    }

    public static void clear() {
        currentTenant.remove();
    }
}
