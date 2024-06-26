package br.com.portfolio.infra.portfolio.factory;

import br.com.portfolio.domain.portfolio.valueobject.AssetPositionVO;
import br.com.portfolio.infra.portfolio.entity.AssetPositionVOEntity;
import br.com.portfolio.infra.shared.factory.IFactory;
import org.springframework.stereotype.Component;

@Component
public class AssetPositionVOFactory implements IFactory<AssetPositionVO, AssetPositionVOEntity> {

    @Override
    public AssetPositionVO toModel(AssetPositionVOEntity entity) {
        return new AssetPositionVO(entity.getQuantity(), entity.getPrice());
    }

    @Override
    public AssetPositionVOEntity toEntity(AssetPositionVO assetPositionVO) {
        return new AssetPositionVOEntity(assetPositionVO.getQuantity(), assetPositionVO.getAveragePurchasePrice());
    }
}
