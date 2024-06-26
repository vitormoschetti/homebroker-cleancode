package br.com.portfolio.domain.portfolio.exception;

import br.com.portfolio.domain.shared.entity.exception.NotFoundException;

public class PortfolioNotFoundException extends NotFoundException {

    public PortfolioNotFoundException(final String message) {
        super(message);
    }
}
