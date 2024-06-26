package br.com.portfolio.domain.customer.event.created;

import br.com.portfolio.domain.shared.event.IRecord;

import java.util.UUID;

public record CustomerCreatedRecord(UUID tenantId) implements IRecord {
}
