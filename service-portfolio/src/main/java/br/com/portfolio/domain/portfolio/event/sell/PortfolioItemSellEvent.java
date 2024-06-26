package br.com.portfolio.domain.portfolio.event.sell;

import br.com.portfolio.domain.shared.event.IEvent;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.UUID;

public class PortfolioItemSellEvent implements IEvent<PortfolioItemSellRecord> {

    private final String eventName;
    private final UUID traceId;
    private final Instant created;
    private final PortfolioItemSellRecord payload;

    public PortfolioItemSellEvent(Long assetId, BigDecimal price, BigDecimal quantity, Instant sellAt) {
        this.eventName = this.getClass().getSimpleName();
        this.traceId = UUID.randomUUID();
        this.created = Instant.now().atOffset(ZoneOffset.UTC).toInstant();
        this.payload = new PortfolioItemSellRecord(assetId, price, quantity, sellAt);
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
    public PortfolioItemSellRecord getPayload() {
        return this.payload;
    }
}
