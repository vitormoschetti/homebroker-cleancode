package br.com.portfolio.application.customer.handler;

import br.com.portfolio.domain.customer.event.changeall.CustomerChangedAllDispatcher;
import br.com.portfolio.domain.customer.event.changeall.CustomerChangedAllEvent;
import br.com.portfolio.infra.kafka.KafkaUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerChangedAllHandler implements CustomerChangedAllDispatcher {

    private final KafkaUtil<UUID, CustomerChangedAllEvent> kafkaUtil;
    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    @Override
    public void dispatch(CustomerChangedAllEvent event) {
        log.info("Receive event={} traceId={} createAt={}", event.getEventName(), event.getTraceId(), event.getInstantCreated());
        log.info("Customer {} changed all", event.getPayload().tenantId());
        kafkaUtil.send(event.getTraceId(), event, topic);
    }
}
