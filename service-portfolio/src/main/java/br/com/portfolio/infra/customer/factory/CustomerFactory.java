package br.com.portfolio.infra.customer.factory;

import br.com.portfolio.domain.customer.entity.Customer;
import br.com.portfolio.infra.customer.entity.CustomerEntity;
import br.com.portfolio.infra.shared.factory.AuditFactory;
import br.com.portfolio.infra.shared.factory.IFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomerFactory implements IFactory<Customer, CustomerEntity> {

    private final AddressVOFactory addressFactory;
    private final AuditFactory auditFactory;

    @Override
    public Customer toModel(CustomerEntity e) {
        return new Customer(e.getId(), e.getTenantId(), e.getName(),
                addressFactory.toModel(e.getAddress()), e.isActive(),
                e.getRewardPoints(), auditFactory.toModel(e.getAudit()));
    }

    @Override
    public CustomerEntity toEntity(Customer c) {
        return new CustomerEntity(c.getId(), c.getTenantId(), c.getName(),
                addressFactory.toEntity(c.getAddress()), c.isActive(),
                c.getRewardPoints(), auditFactory.toEntity(c.getAudit()));
    }
}
