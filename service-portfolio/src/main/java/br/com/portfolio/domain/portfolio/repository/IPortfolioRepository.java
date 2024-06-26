package br.com.portfolio.domain.portfolio.repository;

import br.com.portfolio.domain.portfolio.entity.Portfolio;
import br.com.portfolio.domain.shared.repository.IGenericRepository;

import java.util.UUID;

public interface IPortfolioRepository extends IGenericRepository<Portfolio, UUID> {
    Portfolio findByCustomerId(Long customerId);
}
