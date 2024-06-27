package br.com.log.infra.entity.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.util.UUID;

public class UUIDDynamoConverter implements DynamoDBTypeConverter<String, UUID> {

    @Override
    public String convert(UUID traceId) {
        return traceId.toString();
    }

    @Override
    public UUID unconvert(String traceId) {
        return UUID.fromString(traceId);
    }
}
