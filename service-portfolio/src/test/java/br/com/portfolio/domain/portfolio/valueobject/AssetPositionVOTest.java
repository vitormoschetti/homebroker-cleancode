package br.com.portfolio.domain.portfolio.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class AssetPositionVOTest {

    @Test
    @DisplayName("should have 100 dollars in position")
    void shouldHave100DollarsInTotalInvested() {

        //scenario
        final var assertPosition = new AssetPositionVO();
        final var quantity = BigDecimal.TEN;
        final var price = BigDecimal.TEN;

        //execution
        assertPosition.buy(quantity, price);

        //validation
        assertEquals(new BigDecimal("100"), assertPosition.totalInvested());
        assertEquals(BigDecimal.TEN, assertPosition.getQuantity());
        assertEquals(BigDecimal.TEN, assertPosition.getAveragePurchasePrice());
        assertFalse(assertPosition.hasErrors());

    }

    @Test
    @DisplayName("shouldn't buy when quantity or price are zero")
    void shouldNotBuyWhenQuantityOrPriceAreZero() {

        //scenario
        final var assertPosition = new AssetPositionVO();

        //execution
        assertPosition.buy(BigDecimal.ZERO, BigDecimal.TEN);

        //validation
        assertEquals(BigDecimal.ZERO, assertPosition.totalInvested());
        assertEquals(BigDecimal.ZERO, assertPosition.getQuantity());
        assertEquals(BigDecimal.ZERO, assertPosition.getAveragePurchasePrice());
        assertFalse(assertPosition.hasErrors());

        //execution
        assertPosition.buy(BigDecimal.TEN, BigDecimal.ZERO);

        //validation
        assertEquals(BigDecimal.ZERO, assertPosition.totalInvested());
        assertEquals(BigDecimal.ZERO, assertPosition.getQuantity());
        assertEquals(BigDecimal.ZERO, assertPosition.getAveragePurchasePrice());
        assertFalse(assertPosition.hasErrors());

        //execution
        assertPosition.buy(BigDecimal.ZERO, BigDecimal.ZERO);

        //validation
        assertEquals(BigDecimal.ZERO, assertPosition.totalInvested());
        assertEquals(BigDecimal.ZERO, assertPosition.getQuantity());
        assertEquals(BigDecimal.ZERO, assertPosition.getAveragePurchasePrice());
        assertFalse(assertPosition.hasErrors());
    }

    @Test
    @DisplayName("shouldn't sell when quantity is zero")
    void shouldNotSellWhenQuantityIsZero() {

        //scenario
        final var assertPosition = new AssetPositionVO();

        assertPosition.buy(BigDecimal.TEN, BigDecimal.TEN);

        assertPosition.sell(BigDecimal.ZERO);

        assertEquals(new BigDecimal("100"), assertPosition.totalInvested());
        assertEquals(BigDecimal.TEN, assertPosition.getQuantity());
        assertEquals(BigDecimal.TEN, assertPosition.getAveragePurchasePrice());
        assertFalse(assertPosition.hasErrors());

    }

    @Test
    @DisplayName("shouldn't sell when quantity is greater than position quantity")
    void shouldNotSellWhenQuantityGreaterThanPositionQuantity() {

        //scenario
        final var assertPosition = new AssetPositionVO();

        assertPosition.buy(BigDecimal.TWO, BigDecimal.TEN);

        assertPosition.sell(BigDecimal.TEN);

        assertEquals(new BigDecimal("20"), assertPosition.totalInvested());
        assertEquals(BigDecimal.TWO, assertPosition.getQuantity());
        assertEquals(BigDecimal.TEN, assertPosition.getAveragePurchasePrice());
        assertFalse(assertPosition.hasErrors());

    }

    @Test
    @DisplayName("should have 50 dollars in position after buy 100 and sell 50")
    void shouldHave50DollarsInTotalInvested() {

        //scenario
        final var assertPosition = new AssetPositionVO();
        final var quantity = BigDecimal.TEN;
        final var price = BigDecimal.TEN;

        //execution
        assertPosition.buy(quantity, price);

        //validation
        assertEquals(new BigDecimal("100"), assertPosition.totalInvested());
        assertEquals(BigDecimal.TEN, assertPosition.getQuantity());
        assertEquals(BigDecimal.TEN, assertPosition.getAveragePurchasePrice());
        assertFalse(assertPosition.hasErrors());

        assertPosition.sell(new BigDecimal("5"));

        assertEquals(new BigDecimal("50"), assertPosition.totalInvested());
        assertEquals(new BigDecimal("5"), assertPosition.getQuantity());
        assertEquals(BigDecimal.TEN, assertPosition.getAveragePurchasePrice());
        assertFalse(assertPosition.hasErrors());

    }

    @Test
    @DisplayName("should have zero dollars in position after buy 100 and sell 100")
    void shouldHaveZeroDollarsInTotalInvested() {

        //scenario
        final var assertPosition = new AssetPositionVO();
        final var quantity = BigDecimal.TEN;
        final var price = BigDecimal.TEN;

        //execution
        assertPosition.buy(quantity, price);

        //validation
        assertEquals(new BigDecimal("100"), assertPosition.totalInvested());
        assertEquals(BigDecimal.TEN, assertPosition.getQuantity());
        assertEquals(BigDecimal.TEN, assertPosition.getAveragePurchasePrice());
        assertFalse(assertPosition.hasErrors());

        assertPosition.sell(BigDecimal.TEN);

        assertEquals(BigDecimal.ZERO, assertPosition.totalInvested());
        assertEquals(BigDecimal.ZERO, assertPosition.getQuantity());
        assertEquals(BigDecimal.ZERO, assertPosition.getAveragePurchasePrice());
        assertFalse(assertPosition.hasErrors());

    }

    @Test
    @DisplayName("should calculate the average price according to the operations")
    void shouldCalculateTheAveragePriceAccordingToTheOperations() {

        //scenario
        final var assertPosition = new AssetPositionVO();

        //execution
        assertPosition.buy(BigDecimal.TEN, BigDecimal.TEN);
        assertPosition.buy(BigDecimal.TEN, new BigDecimal(20));
        assertPosition.sell(BigDecimal.ONE);
        assertPosition.buy(new BigDecimal(19), new BigDecimal(10));

        //validation
        assertEquals(new BigDecimal("475.0"), assertPosition.totalInvested());
        assertEquals(new BigDecimal(38), assertPosition.getQuantity());
        assertEquals(new BigDecimal("12.5"), assertPosition.getAveragePurchasePrice());
        assertFalse(assertPosition.hasErrors());

    }

}