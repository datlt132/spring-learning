package com.learning.resource;

import com.learning.dto.TenantDTO;
import com.learning.service.TenantDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tenant")
@RequiredArgsConstructor
public class TenantController {
    private final TenantDomain tenantDomain;

    @PutMapping
    public void createTenant(@RequestBody TenantDTO dto) {
        tenantDomain.createNewTenant(dto);
    }
}
