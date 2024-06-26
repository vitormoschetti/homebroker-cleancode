package br.com.portfolio.domain.customer.valueobject;

import br.com.portfolio.domain.shared.notification.DomainNotificationError;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddressTest {

    @Test
    @DisplayName("should throw domain exception when street is empty")
    public void shouldThrowDomainExceptionWhenStreetEmpty() {

        final var addressVOEmpty = new AddressVO("", "", "", "");
        final var addressVONull = new AddressVO(null, null, null, null);

        assertTrue(addressVOEmpty.hasErrors());
        assertEquals(4, addressVOEmpty.getMessages().size());
        assertEquals(Set.of(
                new DomainNotificationError("Street is required"),
                new DomainNotificationError("City is required"),
                new DomainNotificationError("State is required"),
                new DomainNotificationError("ZipCode is required")
        ), addressVOEmpty.getMessages());

        assertTrue(addressVONull.hasErrors());
        assertEquals(4, addressVONull.getMessages().size());
        assertEquals(Set.of(
                new DomainNotificationError("Street is required"),
                new DomainNotificationError("City is required"),
                new DomainNotificationError("State is required"),
                new DomainNotificationError("ZipCode is required")
        ), addressVONull.getMessages());


    }


}
