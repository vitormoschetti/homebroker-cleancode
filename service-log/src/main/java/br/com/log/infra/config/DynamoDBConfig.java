package br.com.log.infra.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories(basePackages = "br.com.log.infra.repository")
public class DynamoDBConfig {

    @Value("${cloud.aws.dynamodb.endpoint}")
    private String endpoint;

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;


    @Bean
    public AmazonDynamoDB amazonDynamoDB() {

        return AmazonDynamoDBClient.builder()
                .withRegion(region)
                .withCredentials(amazonAWSCredentials())
                .withEndpointConfiguration(amazonAWSEndpoit())
                .build();

    }

    private AwsClientBuilder.EndpointConfiguration amazonAWSEndpoit() {
        return new AwsClientBuilder.EndpointConfiguration(endpoint, region);
    }

    private AWSCredentialsProvider amazonAWSCredentials() {
        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey));
    }


}
