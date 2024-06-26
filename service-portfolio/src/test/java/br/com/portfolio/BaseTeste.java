package br.com.portfolio;

import br.com.portfolio.domain.customer.entity.Customer;
import br.com.portfolio.domain.customer.valueobject.AddressVO;

import java.util.List;

public class BaseTeste {

    protected AddressVO buildAddressVOWith(final String street, final String city, final String state, final String zipCode) {
        return new AddressVO(street, city, state, zipCode);
    }

    protected Customer buildValidCustomer() {
        final var customer = new Customer();
        customer.create("name", "street", "city", "state", "zipcode");
        return customer;
    }

    protected List<Customer> buildValidListCustomer() {
        return List.of(
                this.buildValidCustomer(),
                this.buildValidCustomer(),
                this.buildValidCustomer(),
                this.buildValidCustomer(),
                this.buildValidCustomer(),
                this.buildValidCustomer(),
                this.buildValidCustomer(),
                this.buildValidCustomer(),
                this.buildValidCustomer(),
                this.buildValidCustomer()
        );
    }
}
