package br.com.portfolio.application.customer.usecase;

import br.com.portfolio.application.customer.input.CustomerChangeAddressInput;
import br.com.portfolio.application.shared.usecase.IUseCaseWithParam;
import br.com.portfolio.domain.customer.service.ICustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.util.function.Tuple2;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerChangeAddressUseCase implements IUseCaseWithParam<Tuple2<UUID, CustomerChangeAddressInput>, Void> {

    private final ICustomerService customerService;

    @Override
    public Void execute(Tuple2<UUID, CustomerChangeAddressInput> param) {

        final var tenantId = param.getT1();
        final var input = param.getT2();

        customerService.changeAddress(tenantId, input.street(), input.city(), input.state(), input.zipCode());

        return null;
    }
}
