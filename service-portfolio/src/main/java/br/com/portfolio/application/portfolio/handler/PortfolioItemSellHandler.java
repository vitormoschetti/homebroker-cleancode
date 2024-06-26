package br.com.portfolio.application.portfolio.handler;

import br.com.portfolio.domain.portfolio.event.sell.PortfolioItemSellDispatcher;
import br.com.portfolio.domain.portfolio.event.sell.PortfolioItemSellEvent;
import br.com.portfolio.infra.kafka.KafkaUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class PortfolioItemSellHandler implements PortfolioItemSellDispatcher {

    private final KafkaUtil<UUID, PortfolioItemSellEvent> kafkaUtil;
    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    @Override
    public void dispatch(PortfolioItemSellEvent event) {
        log.info("Receive event={} traceId={} createAt={}", event.getEventName(), event.getTraceId(), event.getInstantCreated());
        log.info("Portfolio item {} sell", event.getPayload());
        kafkaUtil.send(event.getTraceId(), event, topic);
    }
}
