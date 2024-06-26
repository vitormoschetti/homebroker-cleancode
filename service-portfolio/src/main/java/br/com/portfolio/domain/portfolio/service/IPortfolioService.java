package br.com.portfolio.domain.portfolio.service;

import br.com.portfolio.domain.portfolio.entity.Portfolio;
import br.com.portfolio.domain.portfolio.entity.PortfolioItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPortfolioService {

    Portfolio create(Long customerId);

    void buy(UUID portfolioId, Long assetId, BigDecimal quantity, BigDecimal averagePurchasePrice);

    void sell(UUID portfolioId, Long assetId, BigDecimal quantity);

    List<PortfolioItem> listItems(UUID portfolioId);

    Optional<PortfolioItem> findItem(UUID portfolioId, Long assetId);

    Portfolio findPortfolio(Long customerId);

}
