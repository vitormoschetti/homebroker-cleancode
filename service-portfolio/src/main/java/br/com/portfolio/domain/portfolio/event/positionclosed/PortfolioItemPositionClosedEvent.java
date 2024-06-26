package br.com.portfolio.domain.portfolio.event.positionclosed;

import br.com.portfolio.domain.shared.event.IEvent;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.UUID;

public class PortfolioItemPositionClosedEvent implements IEvent<PortfolioItemPositionClosedRecord> {

    private final String eventName;
    private final UUID traceId;
    private final Instant created;
    private final PortfolioItemPositionClosedRecord payload;

    public PortfolioItemPositionClosedEvent(Long assetId, Instant sellAt) {
        this.eventName = this.getClass().getSimpleName();
        this.traceId = UUID.randomUUID();
        this.created = Instant.now().atOffset(ZoneOffset.UTC).toInstant();
        this.payload = new PortfolioItemPositionClosedRecord(assetId, sellAt);
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
        return this.created;
    }

    @Override
    public PortfolioItemPositionClosedRecord getPayload() {
        return this.payload;
    }
}
