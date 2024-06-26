package br.com.portfolio.domain.portfolio.event.buy;

import br.com.portfolio.domain.shared.event.IRecord;

import java.math.BigDecimal;
import java.time.Instant;

public record PortfolioItemBuyRecord(Long assetId, BigDecimal price, BigDecimal quantity, Instant buyAt)
        implements IRecord {

    @Override
    public String toString() {
        return "PortfolioItemBuyRecord{" +
                "assetId=" + assetId +
                ", price=" + price +
                ", quantity=" + quantity +
                ", buyAt=" + buyAt +
                '}';
    }
}
