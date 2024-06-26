package br.com.portfolio.application.portfolio.usecase;

import br.com.portfolio.application.portfolio.output.PortfolioListItemsOutput;
import br.com.portfolio.application.shared.usecase.IUseCaseWithParam;
import br.com.portfolio.domain.portfolio.service.IPortfolioService;
import br.com.portfolio.domain.shared.util.InstantUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class PortfolioListItemsUseCase implements IUseCaseWithParam<UUID, List<PortfolioListItemsOutput>> {

    private final IPortfolioService portfolioService;

    @Override
    public List<PortfolioListItemsOutput> execute(UUID portfolioId) {

        final var portfolioItems = portfolioService.listItems(portfolioId);

        return portfolioItems.stream().map(i -> new PortfolioListItemsOutput(i.getAssetId(), i.getQuantity(), i.getAveragePurchasePrice(), i.totalInvested(),
                InstantUtil.toLocalDate(i.getCreatedAt()), InstantUtil.toLocalDate(i.getUpdatedAt()))).toList();
    }
}
