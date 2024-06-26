package br.com.portfolio.application.portfolio.usecase;

import br.com.portfolio.application.portfolio.output.PortfolioFindPortfolioOutput;
import br.com.portfolio.application.portfolio.output.PortfolioItemOutput;
import br.com.portfolio.application.shared.usecase.IUseCaseWithParam;
import br.com.portfolio.domain.customer.service.ICustomerService;
import br.com.portfolio.domain.portfolio.service.IPortfolioService;
import br.com.portfolio.domain.shared.util.InstantUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class PortfolioFindPortfolioUseCase implements IUseCaseWithParam<UUID, PortfolioFindPortfolioOutput> {

    private final ICustomerService customerService;
    private final IPortfolioService portfolioService;


    @Override
    public PortfolioFindPortfolioOutput execute(UUID tenantId) {

        final var customer = customerService.findByTenantId(tenantId);

        final var portfolio = portfolioService.findPortfolio(customer.getId());

        final var items = portfolio.listItemsAssetId();

        final var itemsOutput = items.stream().map(i -> new PortfolioItemOutput(i.getAssetId(), i.getQuantity(), i.getAveragePurchasePrice(), i.totalInvested(),
                InstantUtil.toLocalDate(i.getCreatedAt()), InstantUtil.toLocalDate(i.getUpdatedAt()))).toList();

        return new PortfolioFindPortfolioOutput(portfolio.getId(), portfolio.quantityItems(), itemsOutput, InstantUtil.toLocalDate(portfolio.getCreatedAt()));
    }
}
