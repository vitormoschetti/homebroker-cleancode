package br.com.portfolio.application.portfolio.handler;

import br.com.portfolio.domain.portfolio.event.positionclosed.PortfolioItemPositionClosedDispatcher;
import br.com.portfolio.domain.portfolio.event.positionclosed.PortfolioItemPositionClosedEvent;
import br.com.portfolio.infra.kafka.KafkaUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class PortfolioItemPositionClosedHandler implements PortfolioItemPositionClosedDispatcher {

    private final KafkaUtil<UUID, PortfolioItemPositionClosedEvent> kafkaUtil;
    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    @Override
    public void dispatch(PortfolioItemPositionClosedEvent event) {
        log.info("Receive event={} traceId={} createAt={}", event.getEventName(), event.getTraceId(), event.getInstantCreated());
        log.info("Portfolio item {} position closed", event.getPayload());
        kafkaUtil.send(event.getTraceId(), event, topic);
    }
}
