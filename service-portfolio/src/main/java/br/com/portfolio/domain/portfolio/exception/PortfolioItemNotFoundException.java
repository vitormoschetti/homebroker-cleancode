package br.com.portfolio.domain.portfolio.exception;

import br.com.portfolio.domain.shared.entity.exception.NotFoundException;

public class PortfolioItemNotFoundException extends NotFoundException {

    public PortfolioItemNotFoundException(final String message) {
        super(message);
    }
}
