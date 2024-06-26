package br.com.portfolio.application.customer.input;

public record CustomerUpdateInput(String name, String street, String city, String state, String zipCode) {
}
