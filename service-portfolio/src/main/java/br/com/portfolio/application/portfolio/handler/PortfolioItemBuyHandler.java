package br.com.portfolio.application.portfolio.handler;

import br.com.portfolio.domain.portfolio.event.buy.PortfolioItemBuyDispatcher;
import br.com.portfolio.domain.portfolio.event.buy.PortfolioItemBuyEvent;
import br.com.portfolio.infra.kafka.KafkaUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class PortfolioItemBuyHandler implements PortfolioItemBuyDispatcher {

    private final KafkaUtil<UUID, PortfolioItemBuyEvent> kafkaUtil;
    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    @Override
    public void dispatch(PortfolioItemBuyEvent event) {
        log.info("Receive event={} traceId={} createAt={}", event.getEventName(), event.getTraceId(), event.getInstantCreated());
        log.info("Portfolio item {} buy", event.getPayload());
        kafkaUtil.send(event.getTraceId(), event, topic);
    }
}
