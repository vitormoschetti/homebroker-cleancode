package br.com.portfolio.application.portfolio.input;

import java.math.BigDecimal;

public record PortfolioSellItemInput(Long assetId, BigDecimal quantity) {
}
