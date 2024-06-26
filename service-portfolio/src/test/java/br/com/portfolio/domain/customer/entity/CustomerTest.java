package br.com.portfolio.domain.customer.entity;


import br.com.portfolio.BaseTeste;
import br.com.portfolio.domain.shared.notification.DomainNotificationError;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomerTest extends BaseTeste {

    @Test
    @DisplayName("should add domain notification when id is empty")
    public void shouldThrowDomainExceptionWhenIdEmpty() {

        final var customer = new Customer();
        customer.create("", "street", "city", "state", "zipcode");

        assertTrue(customer.hasErrors());
        assertEquals(1, customer.getMessages().size());
        assertEquals(Set.of(
                new DomainNotificationError("Name is required")
        ), customer.getMessages());

    }

    @Test
    @DisplayName("should add domain notification when change name is null")
    public void shouldThrowDomainExceptionWhenChangeNameNull() {

        final var customer = this.buildValidCustomer();

        customer.changeName(null);

        assertTrue(customer.hasErrors());
        assertEquals(1, customer.getMessages().size());
        assertEquals(Set.of(
                new DomainNotificationError("Name is required")
        ), customer.getMessages());

    }

    @Test
    @DisplayName("should become customer deactivate")
    public void shouldBecomeCustomerDeactivate() {

        final var customer = this.buildValidCustomer();

        customer.deactivate();

        assertEquals(Boolean.FALSE, customer.isActive());

    }

    @Test
    @DisplayName("should become customer activate")
    public void shouldBecomeCustomerActivate() {

        final var customer = this.buildValidCustomer();

        customer.deactivate();
        customer.activate();

        assertEquals(Boolean.TRUE, customer.isActive());

    }

    @Test
    @DisplayName("should add reward points")
    public void shouldAddRewardPoints() {

        final var customer = this.buildValidCustomer();

        assertEquals(0L, customer.getRewardPoints());

        customer.addRewardPoints(10L);

        assertEquals(10L, customer.getRewardPoints());

        customer.addRewardPoints(10L);

        assertEquals(20L, customer.getRewardPoints());

    }

    @Test
    @DisplayName("should add domain notification when reward points is less than 0")
    public void shouldThrowDomainExceptionWhenRewardPointsIsLessThanZero() {

        final var customer = this.buildValidCustomer();

        customer.addRewardPoints(-10L);

        assertTrue(customer.hasErrors());
        assertEquals(1, customer.getMessages().size());
        assertEquals(Set.of(
                new DomainNotificationError("Reward Points must be greater equal zero")
        ), customer.getMessages());

    }

    @Test
    @DisplayName("should add domain notification when change address is invalid value")
    public void shouldAddDomainNotificationWhenChangeAddressInvalidValue() {
        final var customer = this.buildValidCustomer();

        customer.changeAddress("", "", "", "");

        assertTrue(customer.hasErrors());
        assertEquals(4, customer.getMessages().size());
        assertEquals(Set.of(
                new DomainNotificationError("Street is required"),
                new DomainNotificationError("City is required"),
                new DomainNotificationError("State is required"),
                new DomainNotificationError("ZipCode is required")
        ), customer.getMessages());

    }

    @Test
    @DisplayName("should add domain notification when change name is invalid value")
    public void shouldAddDomainNotificationWhenChangeNameInvalidValue() {
        final var customer = this.buildValidCustomer();

        customer.changeName("");

        assertTrue(customer.hasErrors());
        assertEquals(1, customer.getMessages().size());
        assertEquals(Set.of(
                new DomainNotificationError("Name is required")
        ), customer.getMessages());

    }

    @Test
    @DisplayName("should add domain notification when change all is invalid value")
    public void shouldAddDomainNotificationWhenChangeAllInvalidValue() {
        final var customer = this.buildValidCustomer();

        customer.changeAll("", "", "", "", "");

        assertTrue(customer.hasErrors());
        assertEquals(5, customer.getMessages().size());
        assertEquals(Set.of(
                new DomainNotificationError("Name is required"),
                new DomainNotificationError("Street is required"),
                new DomainNotificationError("City is required"),
                new DomainNotificationError("State is required"),
                new DomainNotificationError("ZipCode is required")
        ), customer.getMessages());

    }

}
