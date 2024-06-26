package br.com.portfolio.application.portfolio.input;

import java.math.BigDecimal;

public record PortfolioBuyItemInput(Long assetId, BigDecimal quantity, BigDecimal averagePurchasePrice) {
}
