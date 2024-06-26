package br.com.portfolio.application.portfolio.usecase;

import br.com.portfolio.application.portfolio.input.PortfolioBuyItemInput;
import br.com.portfolio.application.shared.usecase.IUseCaseWithParam;
import br.com.portfolio.domain.portfolio.service.IPortfolioService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.util.function.Tuple2;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class PortfolioBuyItemUseCase implements IUseCaseWithParam<Tuple2<UUID, PortfolioBuyItemInput>, Void> {

    private final IPortfolioService portfolioService;

    @Override
    public Void execute(Tuple2<UUID, PortfolioBuyItemInput> param) {

        final var portfolioId = param.getT1();
        final var input = param.getT2();

        portfolioService.buy(portfolioId, input.assetId(), input.quantity(), input.averagePurchasePrice());

        return null;
    }
}
