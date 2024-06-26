package br.com.portfolio.domain.customer.event.changedaddress;

import br.com.portfolio.domain.shared.event.IRecord;

import java.util.UUID;

public record CustomerChangedAddressRecord(UUID tenantId) implements IRecord {
}
