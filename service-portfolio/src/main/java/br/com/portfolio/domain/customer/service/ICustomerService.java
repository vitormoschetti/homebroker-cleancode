package br.com.portfolio.domain.customer.service;

import br.com.portfolio.domain.customer.entity.Customer;

import java.util.List;
import java.util.UUID;

public interface ICustomerService {

    Customer create(final String name, final String street, final String city, final String state, final String zipCode);

    void changeAddress(final UUID tenantId, final String street, final String city, final String state, final String zipCode);

    Customer findByTenantId(final UUID tenantId);

    Customer update(final UUID tenantId, final String name, final String street,
                    final String state, final String city, final String zipCode);

    List<Customer> findAll();

}
