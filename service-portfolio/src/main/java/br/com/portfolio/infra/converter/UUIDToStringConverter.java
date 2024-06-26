package br.com.portfolio.infra.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

@Converter(autoApply = true)
public class UUIDToStringConverter implements AttributeConverter<UUID, String> {

    @Override
    public String convertToDatabaseColumn(UUID tenantId) {
        return tenantId.toString();
    }

    @Override
    public UUID convertToEntityAttribute(String tenantId) {
        return UUID.fromString(tenantId);
    }
}
