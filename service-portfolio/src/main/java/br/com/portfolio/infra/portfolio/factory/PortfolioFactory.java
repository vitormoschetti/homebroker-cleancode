package br.com.portfolio.infra.portfolio.factory;

import br.com.portfolio.domain.portfolio.entity.Portfolio;
import br.com.portfolio.infra.portfolio.entity.PortfolioEntity;
import br.com.portfolio.infra.shared.factory.AuditFactory;
import br.com.portfolio.infra.shared.factory.IFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class PortfolioFactory implements IFactory<Portfolio, PortfolioEntity> {

    private final PortfolioItemFactory portfolioItemFactory;
    private final AuditFactory auditFactory;

    @Override
    public Portfolio toModel(PortfolioEntity entity) {

        final var items = entity.getItems().stream().map(portfolioItemFactory::toModel).collect(Collectors.toSet());

        return new Portfolio(entity.getId(), entity.getCustomerId(), items,
                auditFactory.toModel(entity.getAudit())
        );
    }

    @Override
    public PortfolioEntity toEntity(Portfolio portfolio) {

        final var items = portfolio.listItemsAssetId().stream().map(portfolioItemFactory::toEntity).collect(Collectors.toSet());

        return new PortfolioEntity(portfolio.getId(), portfolio.getCustomerId(), items, auditFactory.toEntity(portfolio.getAudit()));
    }
}
