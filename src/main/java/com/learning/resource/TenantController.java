package com.learning.resource;

import com.learning.base.dto.ServerResponse;
import com.learning.dto.TenantDTO;
import com.learning.service.TenantDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ServerResponse> createTenant(@RequestBody TenantDTO dto) {
        return ResponseEntity.ok(ServerResponse.successWith(tenantDomain.createNewTenant(dto)));
    }
}
