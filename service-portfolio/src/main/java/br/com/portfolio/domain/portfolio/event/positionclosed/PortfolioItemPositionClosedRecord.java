package br.com.portfolio.domain.portfolio.event.positionclosed;

import br.com.portfolio.domain.shared.event.IRecord;

import java.time.Instant;

public record PortfolioItemPositionClosedRecord(Long assetId, Instant sellAt)
        implements IRecord {
    @Override
    public String toString() {
        return "PortfolioItemPositionClosedRecord{" +
                "assetId=" + assetId +
                ", sellAt=" + sellAt +
                '}';
    }
}
