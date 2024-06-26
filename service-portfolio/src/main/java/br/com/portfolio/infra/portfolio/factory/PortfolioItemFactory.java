package br.com.portfolio.infra.portfolio.factory;

import br.com.portfolio.domain.portfolio.entity.PortfolioItem;
import br.com.portfolio.infra.portfolio.entity.PortfolioEntity;
import br.com.portfolio.infra.portfolio.entity.PortfolioItemEntity;
import br.com.portfolio.infra.shared.factory.AuditFactory;
import br.com.portfolio.infra.shared.factory.IFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PortfolioItemFactory implements IFactory<PortfolioItem, PortfolioItemEntity> {

    private final AssetPositionVOFactory assetPositionVOFactory;
    private final AuditFactory auditFactory;

    @Override
    public PortfolioItem toModel(PortfolioItemEntity entity) {
        return new PortfolioItem(entity.getId(), assetPositionVOFactory.toModel(entity.getPosition()),
                auditFactory.toModel(entity.getAudit()), entity.getPortfolio().getId());
    }

    @Override
    public PortfolioItemEntity toEntity(PortfolioItem item) {

        final var portfolioEntity = new PortfolioEntity();
        portfolioEntity.setId(item.getPortfolioId());

        return new PortfolioItemEntity(item.getAssetId(), portfolioEntity,
                assetPositionVOFactory.toEntity(item.getPosition()),
                auditFactory.toEntity(item.getAudit()));
    }
}
