package com.learning.repository;

import com.learning.entity.TenantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends JpaRepository<TenantEntity, Long> {
    TenantEntity findByTenantId(String tenantId);
}
