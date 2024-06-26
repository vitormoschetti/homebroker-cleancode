package br.com.portfolio.domain.portfolio.event.buy;

import br.com.portfolio.domain.shared.event.IEvent;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.UUID;

public class PortfolioItemBuyEvent implements IEvent<PortfolioItemBuyRecord> {

    private final String eventName;
    private final UUID traceId;
    private final Instant created;
    private final PortfolioItemBuyRecord payload;

    public PortfolioItemBuyEvent(Long assetId, BigDecimal price, BigDecimal quantity, Instant buyAt) {
        this.eventName = this.getClass().getSimpleName();
        this.traceId = UUID.randomUUID();
        this.created = Instant.now().atOffset(ZoneOffset.UTC).toInstant();
        this.payload = new PortfolioItemBuyRecord(assetId, price, quantity, buyAt);
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
    public PortfolioItemBuyRecord getPayload() {
        return this.payload;
    }
}
