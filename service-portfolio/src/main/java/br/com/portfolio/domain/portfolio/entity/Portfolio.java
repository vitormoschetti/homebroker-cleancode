package br.com.portfolio.domain.portfolio.entity;

import br.com.portfolio.domain.shared.entity.BaseEntity;
import br.com.portfolio.domain.shared.entity.IAggregateRoot;
import br.com.portfolio.domain.shared.notification.DomainNotification;
import br.com.portfolio.domain.shared.valueobject.AuditTimestamps;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

public class Portfolio extends BaseEntity implements IAggregateRoot {

    private final UUID id;
    private final Long customerId;
    private final Set<PortfolioItem> items;
    private final AuditTimestamps audit;

    public Portfolio(Long customerId) {
        super(new DomainNotification());
        this.id = UUID.randomUUID();
        this.customerId = customerId;
        this.audit = new AuditTimestamps();
        this.items = new HashSet<>();
    }

    public Portfolio(UUID id, Long customerId, Set<PortfolioItem> items, AuditTimestamps audit) {
        super(new DomainNotification());
        this.id = id;
        this.customerId = customerId;
        this.items = items;
        this.audit = audit;
    }

    public PortfolioItem buy(Long assetId, BigDecimal quantity, BigDecimal averagePurchasePrice) {

        final var item = this.items.stream()
                .filter(portfolioItem -> portfolioItem.getAssetId().equals(assetId))
                .findFirst().orElse(new PortfolioItem(assetId, this.getId()));

        item.buy(quantity, averagePurchasePrice);

        this.items.add(item);

        this.audit.updateNow();

        this.validate();

        return item;
    }

    public PortfolioItem sell(Long assetId, BigDecimal quantity) {
        final var item = this.items.stream()
                .filter(portfolioItem -> portfolioItem.getAssetId().equals(assetId))
                .findFirst().orElse(null);

        if (Objects.nonNull(item)) {

            item.sell(quantity);

            this.items.add(item);

            this.clearPositionsZeroQuantity();

            this.audit.updateNow();

            this.validate();
        }
        return item;
    }

    private void clearPositionsZeroQuantity() {
        this.items.removeIf(portfolioItem -> portfolioItem.getQuantity().equals(BigDecimal.ZERO));
    }

    public List<PortfolioItem> listItemsAssetId() {
        return this.items.stream().toList();
    }

    public Optional<PortfolioItem> findItem(Long assetId) {
        return this.items.stream().filter(item -> item.getAssetId().equals(assetId))
                .findFirst();
    }

    public int quantityItems() {
        return items.size();
    }

    public UUID getId() {
        return this.id;
    }

    public Long getCustomerId() {
        return this.customerId;
    }

    public Instant getCreatedAt() {
        return this.audit.getCreatedAt();
    }

    public Instant getUpdatedAt() {
        return this.audit.getUpdatedAt();
    }

    public AuditTimestamps getAudit() {
        return audit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Portfolio portfolio = (Portfolio) o;
        return Objects.equals(id, portfolio.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    protected void validate() {

    }
}
