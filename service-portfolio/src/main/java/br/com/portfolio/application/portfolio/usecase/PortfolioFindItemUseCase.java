package br.com.portfolio.application.portfolio.usecase;

import br.com.portfolio.application.portfolio.output.PortfolioFindItemOutput;
import br.com.portfolio.application.shared.usecase.IUseCaseWithParam;
import br.com.portfolio.domain.portfolio.service.IPortfolioService;
import br.com.portfolio.domain.shared.util.InstantUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.util.function.Tuple2;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class PortfolioFindItemUseCase implements IUseCaseWithParam<Tuple2<UUID, Long>, List<PortfolioFindItemOutput>> {

    private final IPortfolioService portfolioService;

    @Override
    public List<PortfolioFindItemOutput> execute(Tuple2<UUID, Long> param) {

        final var portfolioId = param.getT1();
        final var assetId = param.getT2();

        final var portfolioItems = portfolioService.findItem(portfolioId, assetId);

        return portfolioItems.stream().map(i -> new PortfolioFindItemOutput(i.getAssetId(), i.getQuantity(), i.getAveragePurchasePrice(), i.totalInvested(),
                InstantUtil.toLocalDate(i.getCreatedAt()), InstantUtil.toLocalDate(i.getUpdatedAt()))).toList();
    }
}
