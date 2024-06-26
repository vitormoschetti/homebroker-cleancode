package br.com.portfolio.domain.portfolio.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PortfolioItemTest {

    @Test
    @DisplayName("Position zero when item was created")
    void positionZeroWhenItemCreated() {

        //scenario
        final var item = new PortfolioItem(1L, UUID.randomUUID());

        //validation
        assertEquals(1L, item.getAssetId());
        assertEquals(BigDecimal.ZERO, item.getQuantity());
        assertEquals(BigDecimal.ZERO, item.getAveragePurchasePrice());
        assertEquals(BigDecimal.ZERO, item.totalInvested());
        assertNotNull(item.getCreatedAt());
        assertNull(item.getUpdatedAt());
        assertFalse(item.hasErrors());

    }

    @Test
    @DisplayName("Buy quantity 10, price 10 and total invested 100")
    void buyQuantity10Price10AndTotalInvested100() {

        //scenario
        final var item = new PortfolioItem(1L, UUID.randomUUID());

        //execution
        item.buy(BigDecimal.TEN, BigDecimal.TEN);

        //validation
        assertEquals(1L, item.getAssetId());
        assertEquals(BigDecimal.TEN, item.getQuantity());
        assertEquals(BigDecimal.TEN, item.getAveragePurchasePrice());
        assertEquals(new BigDecimal("100"), item.totalInvested());
        assertNotNull(item.getCreatedAt());
        assertNotNull(item.getUpdatedAt());
        assertFalse(item.hasErrors());

    }

    @Test
    @DisplayName("Buy quantity 10, price 10 and total invested 100 and sell 10")
    void buyQuantity10Price10AndTotalInvested100AndSell10() {

        //scenario
        final var item = new PortfolioItem(1L, UUID.randomUUID());

        //execution
        item.buy(BigDecimal.TEN, BigDecimal.TEN);
        item.sell(BigDecimal.TEN);

        //validation
        assertEquals(1L, item.getAssetId());
        assertEquals(BigDecimal.ZERO, item.getQuantity());
        assertEquals(BigDecimal.ZERO, item.getAveragePurchasePrice());
        assertEquals(BigDecimal.ZERO, item.totalInvested());
        assertNotNull(item.getCreatedAt());
        assertNotNull(item.getUpdatedAt());
        assertFalse(item.hasErrors());

    }

}