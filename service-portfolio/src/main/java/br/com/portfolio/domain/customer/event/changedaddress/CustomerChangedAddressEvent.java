package br.com.portfolio.domain.customer.event.changedaddress;

import br.com.portfolio.domain.shared.event.IEvent;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.UUID;

public class CustomerChangedAddressEvent implements IEvent<CustomerChangedAddressRecord> {

    private final String eventName;
    private final UUID traceId;
    private final CustomerChangedAddressRecord payload;
    private final Instant instantCreated;

    public CustomerChangedAddressEvent(UUID tenantId) {
        this.eventName = this.getClass().getSimpleName().toLowerCase();
        this.traceId = UUID.randomUUID();
        this.instantCreated = Instant.now().atOffset(ZoneOffset.UTC).toInstant();
        this.payload = new CustomerChangedAddressRecord(tenantId);
    }

    @Override
    public String getEventName() {
        return this.eventName;
    }

    @Override
    public UUID getTraceId() {
        return this.traceId;
    }

    @Override
    public Instant getInstantCreated() {
        return this.instantCreated;
    }

    @Override
    public CustomerChangedAddressRecord getPayload() {
        return this.payload;
    }
}
