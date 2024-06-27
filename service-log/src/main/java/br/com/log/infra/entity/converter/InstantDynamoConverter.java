package br.com.log.infra.entity.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.time.Instant;

public class InstantDynamoConverter implements DynamoDBTypeConverter<String, Instant> {

    @Override
    public String convert(Instant createAt) {
        return createAt.toString();
    }

    @Override
    public Instant unconvert(String createAt) {
        return Instant.parse(createAt);
    }
}
