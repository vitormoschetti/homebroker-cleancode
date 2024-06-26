package br.com.portfolio.infra.customer.repository;

import br.com.portfolio.infra.customer.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerPostgresRepository extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByTenantId(UUID tenantId);
}
