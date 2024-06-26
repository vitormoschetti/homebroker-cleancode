package br.com.portfolio.domain.customer.repository;

import br.com.portfolio.domain.customer.entity.Customer;
import br.com.portfolio.domain.shared.repository.IGenericRepository;

import java.util.UUID;

public interface ICustomerRepository extends IGenericRepository<Customer, Long> {

    Customer findByTenantId(UUID tenantId);
}
