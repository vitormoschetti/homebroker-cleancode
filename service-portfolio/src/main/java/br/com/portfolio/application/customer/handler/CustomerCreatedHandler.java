package br.com.portfolio.application.customer.handler;

import br.com.portfolio.domain.customer.event.created.CustomerCreatedDispatcher;
import br.com.portfolio.domain.customer.event.created.CustomerCreatedEvent;
import br.com.portfolio.infra.kafka.KafkaUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerCreatedHandler implements CustomerCreatedDispatcher {

    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    private final KafkaUtil<UUID, CustomerCreatedEvent> kafkaUtil;

    @Override
    public void dispatch(CustomerCreatedEvent event) {
        log.info("Receive event={} traceId={} createAt={}", event.getEventName(), event.getTraceId(), event.getInstantCreated());
        log.info("Customer {} created", event.getPayload().tenantId());

        kafkaUtil.send(event.getTraceId(), event, topic);
    }
}
