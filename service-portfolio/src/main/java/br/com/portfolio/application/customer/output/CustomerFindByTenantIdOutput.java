package br.com.portfolio.application.customer.output;

public record CustomerFindByTenantIdOutput(String name, AddressVOOutput address, boolean status,
                                           long rewardPoints) {
}
