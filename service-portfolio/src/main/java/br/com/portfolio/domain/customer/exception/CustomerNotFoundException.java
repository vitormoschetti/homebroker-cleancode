package br.com.portfolio.domain.customer.exception;

import br.com.portfolio.domain.shared.entity.exception.NotFoundException;

public class CustomerNotFoundException extends NotFoundException {

    public CustomerNotFoundException(final String message) {
        super(message);
    }
}
