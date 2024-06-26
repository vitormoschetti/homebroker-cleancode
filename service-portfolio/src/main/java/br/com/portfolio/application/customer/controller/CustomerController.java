package br.com.portfolio.application.customer.controller;

import br.com.portfolio.application.customer.input.CreateCustomerInput;
import br.com.portfolio.application.customer.input.CustomerChangeAddressInput;
import br.com.portfolio.application.customer.input.CustomerUpdateInput;
import br.com.portfolio.application.customer.output.CreateUserOutput;
import br.com.portfolio.application.customer.output.CustomerFindByTenantIdOutput;
import br.com.portfolio.application.customer.usecase.CustomerChangeAddressUseCase;
import br.com.portfolio.application.customer.usecase.CustomerCreateUseCase;
import br.com.portfolio.application.customer.usecase.CustomerFindUseCase;
import br.com.portfolio.application.customer.usecase.CustomerUpdateUseCase;
import br.com.portfolio.application.shared.response.Response;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.util.function.Tuples;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerCreateUseCase createUseCase;
    private final CustomerFindUseCase findUseCase;
    private final CustomerUpdateUseCase updateUseCase;
    private final CustomerChangeAddressUseCase changeAddressUseCase;


    @PostMapping
    public ResponseEntity<Response<CreateUserOutput>> save(@RequestBody CreateCustomerInput input) {

        final var response = createUseCase.execute(input);

        return Response.successResponse(HttpStatus.CREATED, response);

    }

    @GetMapping
    public ResponseEntity<Response<CustomerFindByTenantIdOutput>> findCustomer(@RequestHeader(name = "tenantId") UUID tenantId) {

        final var response = findUseCase.execute(tenantId);

        return Response.successResponse(HttpStatus.OK, response);

    }

    @PutMapping
    public ResponseEntity<Response<Void>> putCustomer(@RequestHeader(name = "tenantId") UUID tenantId, @RequestBody CustomerUpdateInput input) {

        updateUseCase.execute(Tuples.of(tenantId, input));

        return Response.successResponse(HttpStatus.OK);

    }

    @PatchMapping("/new-address")
    public ResponseEntity<Response<Void>> patchCustomerAddress(@RequestHeader(name = "tenantId") UUID tenantId, @RequestBody CustomerChangeAddressInput input) {

        changeAddressUseCase.execute(Tuples.of(tenantId, input));

        return Response.successResponse(HttpStatus.OK);

    }


}

