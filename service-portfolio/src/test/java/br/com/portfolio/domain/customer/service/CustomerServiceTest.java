package br.com.portfolio.domain.customer.service;//package br.com.ddd.domain.customer.service;

import br.com.portfolio.BaseTeste;
import br.com.portfolio.domain.customer.entity.Customer;
import br.com.portfolio.domain.customer.event.changeall.CustomerChangedAllDispatcher;
import br.com.portfolio.domain.customer.event.changedaddress.CustomerChangedAddressDispatcher;
import br.com.portfolio.domain.customer.event.created.CustomerCreatedDispatcher;
import br.com.portfolio.domain.customer.repository.ICustomerRepository;
import br.com.portfolio.domain.customer.valueobject.AddressVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest extends BaseTeste {

    @InjectMocks
    CustomerService customerService;

    @Mock
    ICustomerRepository repository;

    @Captor
    ArgumentCaptor<Customer> customerCaptor;

    @Mock
    CustomerChangedAllDispatcher customerChangedAllDispatcher;

    @Mock
    CustomerChangedAddressDispatcher customerChangedAddressDispatcher;

    @Mock
    CustomerCreatedDispatcher customerCreatedDispatcher;


    @Test
    @DisplayName("should created customer")
    void shouldNotifyHandlersAfterCreatedCustomer() {

        //scenario
        doNothing().when(repository).create(any(Customer.class));

        //execution
        final var customer = customerService.create("test", "street", "city", "state", "zipcode");

        //validation

        Assertions.assertNotNull(customer.getTenantId());
        Assertions.assertEquals("test", customer.getName());
        Assertions.assertEquals(new AddressVO("street", "city", "state", "zipcode"), customer.getAddress());
        Assertions.assertEquals(0L, customer.getRewardPoints());
        Assertions.assertEquals(Boolean.TRUE, customer.isActive());

    }

    @Test
    @DisplayName("should changed address")
    void shouldNotifyHandlersAfterChangedAddress() {

        //scenario
        final var customerMock = this.buildValidCustomer();

        when(repository.findByTenantId(any(UUID.class))).thenReturn(customerMock);

        doNothing().when(repository).update(any(Customer.class));

        //execution
        this.customerService.changeAddress(UUID.randomUUID(), "foo", "foo", "foo", "foo");

        //validation

        verify(repository, times(1)).update(customerCaptor.capture());

        final var customer = customerCaptor.getValue();

        //TODO captor repository save e validate
        Assertions.assertEquals(customer.getTenantId(), customerMock.getTenantId());
        Assertions.assertEquals(customer.getName(), customerMock.getName());
        Assertions.assertEquals(customer.getAddress(), customerMock.getAddress());
        Assertions.assertEquals(customer.getRewardPoints(), customerMock.getRewardPoints());
        Assertions.assertEquals(customer.isActive(), customerMock.isActive());

    }

    @Test
    @DisplayName("should changed all")
    void shouldChangedAll() {

        //scenario
        final var customerMock = this.buildValidCustomer();

        when(repository.findByTenantId(any(UUID.class))).thenReturn(customerMock);

        doNothing().when(repository).update(any(Customer.class));

        //execution
        final var customer = this.customerService.update(UUID.randomUUID(), "foo", "foo",
                "foo", "foo", "foo");

        //validation
        Assertions.assertEquals(customer.getTenantId(), customerMock.getTenantId());
        Assertions.assertEquals(customer.getName(), customerMock.getName());
        Assertions.assertEquals(customer.getAddress(), customerMock.getAddress());
        Assertions.assertEquals(customer.getRewardPoints(), customerMock.getRewardPoints());
        Assertions.assertEquals(customer.isActive(), customerMock.isActive());

    }


    @Test
    @DisplayName("Should find by id")
    void shouldFindByTenantId() {

        //scenario
        final var customerMock = buildValidCustomer();
        when(repository.findByTenantId(any(UUID.class))).thenReturn(customerMock);

        //execution
        final var customer = this.customerService.findByTenantId(UUID.randomUUID());

        //validation
        Assertions.assertEquals(customer.getTenantId(), customerMock.getTenantId());
        Assertions.assertEquals(customer.getName(), customerMock.getName());
        Assertions.assertEquals(customer.getAddress(), customerMock.getAddress());
        Assertions.assertEquals(customer.getRewardPoints(), customerMock.getRewardPoints());
        Assertions.assertEquals(customer.isActive(), customerMock.isActive());

    }

    @Test
    @DisplayName("Should find all")
    void shouldFindAll() {

        //scenario
        final var customersMocks = buildValidListCustomer();
        when(repository.findAll()).thenReturn(customersMocks);

        //execution
        final var customers = this.customerService.findAll();

        //validation

        for (int i = 0; i < customersMocks.size(); i++) {
            Assertions.assertEquals(customers.get(i).getTenantId(), customersMocks.get(i).getTenantId());
            Assertions.assertEquals(customers.get(i).getName(), customersMocks.get(i).getName());
            Assertions.assertEquals(customers.get(i).getAddress(), customersMocks.get(i).getAddress());
            Assertions.assertEquals(customers.get(i).getRewardPoints(), customersMocks.get(i).getRewardPoints());
            Assertions.assertEquals(customers.get(i).isActive(), customersMocks.get(i).isActive());
        }


    }

}
