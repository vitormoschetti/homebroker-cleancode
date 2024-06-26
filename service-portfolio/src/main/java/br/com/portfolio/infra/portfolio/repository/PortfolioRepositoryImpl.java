package br.com.portfolio.infra.portfolio.repository;

import br.com.portfolio.domain.portfolio.entity.Portfolio;
import br.com.portfolio.domain.portfolio.repository.IPortfolioRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class PortfolioRepositoryImpl implements IPortfolioRepository {

    @Override
    public Portfolio findByCustomerId(Long customerId) {
        return null;
    }

    @Override
    public void create(Portfolio model) {

    }

    @Override
    public void update(Portfolio model) {

    }

    @Override
    public Portfolio findById(UUID id) {
        return null;
    }

    @Override
    public List<Portfolio> findAll() {
        return List.of();
    }
}
