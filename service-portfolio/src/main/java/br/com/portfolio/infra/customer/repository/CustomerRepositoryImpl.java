package br.com.portfolio.infra.customer.repository;

import br.com.portfolio.domain.customer.entity.Customer;
import br.com.portfolio.domain.customer.repository.ICustomerRepository;
import br.com.portfolio.infra.customer.factory.CustomerFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class CustomerRepositoryImpl implements ICustomerRepository {

    private final CustomerPostgresRepository repository;
    private final CustomerFactory factory;

    @Override
    public Customer findByTenantId(UUID tenantId) {
        final var entity = repository.findByTenantId(tenantId);
        return entity.map(factory::toModel).orElse(null);
    }

    @Override
    public void create(Customer customer) {
        repository.save(factory.toEntity(customer));
    }

    @Override
    public void update(Customer customer) {
        repository.save(factory.toEntity(customer));
    }

    @Override
    public Customer findById(Long id) {
        final var entity = repository.findById(id);
        return entity.map(factory::toModel).orElse(null);
    }

    @Override
    public List<Customer> findAll() {
        final var entities = repository.findAll();
        return entities.stream().map(factory::toModel).toList();
    }
}
