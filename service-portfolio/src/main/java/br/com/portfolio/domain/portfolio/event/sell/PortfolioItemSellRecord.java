package br.com.portfolio.domain.portfolio.event.sell;

import br.com.portfolio.domain.shared.event.IRecord;

import java.math.BigDecimal;
import java.time.Instant;

public record PortfolioItemSellRecord(Long assetId, BigDecimal price, BigDecimal quantity, Instant sellAt)
        implements IRecord {

    @Override
    public String toString() {
        return "PortfolioItemSellRecord{" +
                "assetId=" + assetId +
                ", price=" + price +
                ", quantity=" + quantity +
                ", sellAt=" + sellAt +
                '}';
    }
}
