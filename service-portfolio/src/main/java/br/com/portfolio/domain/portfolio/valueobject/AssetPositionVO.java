package br.com.portfolio.domain.portfolio.valueobject;

import br.com.portfolio.domain.shared.entity.BaseEntity;
import br.com.portfolio.domain.shared.entity.IValueObject;
import br.com.portfolio.domain.shared.notification.DomainNotification;
import br.com.portfolio.domain.shared.notification.DomainNotificationError;

import java.math.BigDecimal;
import java.math.MathContext;

public class AssetPositionVO extends BaseEntity implements IValueObject {

    private BigDecimal quantity;
    private BigDecimal averagePurchasePrice;

    public AssetPositionVO() {
        super(new DomainNotification());
        this.quantity = new BigDecimal(0);
        this.averagePurchasePrice = new BigDecimal(0);
    }

    public AssetPositionVO(BigDecimal quantity, BigDecimal price) {
        super(new DomainNotification());
        this.quantity = quantity;
        this.averagePurchasePrice = price;
    }

    public void buy(BigDecimal quantity, BigDecimal averagePurchasePrice) {
        if (quantityGreaterThanZero(quantity) && priceGreaterThanZero(averagePurchasePrice)) {
            calculateAveragePurchasePrice(quantity, averagePurchasePrice);
        }
        this.validate();
    }

    public void sell(BigDecimal quantity) {
        if (quantityGreaterThanZero(quantity) && this.quantity.compareTo(quantity) >= 0) {
            this.quantity = this.quantity.subtract(quantity);
            if (this.quantity.compareTo(BigDecimal.ZERO) == 0) {
                this.averagePurchasePrice = BigDecimal.ZERO;
            }
        }
        this.validate();
    }

    public BigDecimal totalInvested() {
        return this.quantity.multiply(this.averagePurchasePrice);
    }

    private BigDecimal averagePriceOld() {
        return this.averagePurchasePrice.multiply(this.quantity);
    }

    private BigDecimal averagePriceNew(BigDecimal quantity, BigDecimal averagePurchasePrice) {
        return averagePurchasePrice.multiply(quantity);
    }

    private BigDecimal sumAllQuantity(BigDecimal quantity) {
        return this.quantity.add(quantity);
    }

    private void calculateAveragePurchasePrice(BigDecimal quantity, BigDecimal averagePurchasePrice) {
        final var sumAll = this.averagePriceOld().add(this.averagePriceNew(quantity, averagePurchasePrice));
        final var quantityAll = this.sumAllQuantity(quantity);
        this.averagePurchasePrice = sumAll.divide(quantityAll, MathContext.DECIMAL32);
        this.quantity = quantityAll;
    }

    private boolean quantityGreaterThanZero(BigDecimal quantity) {
        return priceGreaterThanZero(quantity);
    }

    private boolean priceGreaterThanZero(BigDecimal averagePurchasePrice) {
        return averagePurchasePrice.compareTo(BigDecimal.ZERO) > 0;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public BigDecimal getAveragePurchasePrice() {
        return averagePurchasePrice;
    }

    @Override
    protected void validate() {
        if (quantity.compareTo(BigDecimal.ZERO) < 0)
            this.addMessage(new DomainNotificationError("Quantity must be greater than zero"));
        if (averagePurchasePrice.compareTo(BigDecimal.ZERO) < 0)
            this.addMessage(new DomainNotificationError("Average purchase price must be greater than zero"));
    }


}
