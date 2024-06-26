package br.com.portfolio.infra.kafka;

import br.com.portfolio.domain.shared.event.IEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;

@Slf4j
@Component
@AllArgsConstructor
public class KafkaUtil<K, V extends IEvent> implements Serializable {

    private final KafkaTemplate<K, V> template;

    public void send(final K key, final V value, final String topic) {

        this.template.send(topic, key, value)
                .whenComplete((_, error) -> {
                    if (Objects.nonNull(error)) {
                        log.error("Error while sending message={} with payload={}", value.getEventName(), value.getPayload(), error);
                    } else {
                        log.info("Successfully sent message={}", value.getEventName());
                    }
                });

    }

}
