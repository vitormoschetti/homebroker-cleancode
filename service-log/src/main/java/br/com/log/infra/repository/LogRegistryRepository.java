package br.com.log.infra.repository;

import br.com.log.infra.entity.LogRegistryEntity;
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBCrudRepository;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@EnableScan
@Repository
public interface LogRegistryRepository extends DynamoDBCrudRepository<LogRegistryEntity, UUID> {
}
