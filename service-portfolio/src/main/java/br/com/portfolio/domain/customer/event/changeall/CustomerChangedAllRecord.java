package br.com.portfolio.domain.customer.event.changeall;

import br.com.portfolio.domain.shared.event.IRecord;

import java.util.UUID;

public record CustomerChangedAllRecord(UUID tenantId) implements IRecord {
}
