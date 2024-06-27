package br.com.log.infra.entity;

import br.com.log.infra.entity.converter.InstantDynamoConverter;
import br.com.log.infra.entity.converter.UUIDDynamoConverter;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@AllArgsConstructor
@DynamoDBTable(tableName = "LogRegistry")
public class LogRegistryEntity {

    @DynamoDBHashKey
    @DynamoDBTypeConverted(converter = UUIDDynamoConverter.class)
    private final UUID traceId;

    @DynamoDBAttribute
    private final String eventName;

    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = InstantDynamoConverter.class)
    private final Instant instantCreated;

    @DynamoDBAttribute
    private final String payload;

}
