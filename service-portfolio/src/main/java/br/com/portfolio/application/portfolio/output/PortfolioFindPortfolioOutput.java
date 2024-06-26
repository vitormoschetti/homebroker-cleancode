package br.com.portfolio.application.portfolio.output;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record PortfolioFindPortfolioOutput(UUID portfolioId, int quantityItems, List<PortfolioItemOutput> items,
                                           LocalDate createdAt) {
}
