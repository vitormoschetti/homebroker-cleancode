package br.com.portfolio.infra.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    @Bean
    public NewTopic defaultTopic() {
        return new NewTopic(topic, 6, (short) 1);
    }

}
