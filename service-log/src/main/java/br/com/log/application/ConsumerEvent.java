package br.com.log.application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Service
public class ConsumerEvent {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting()
            .registerTypeAdapter(UUID.class, new UUIDAdapter())
            .registerTypeAdapter(Instant.class, new InstantAdapter()).create();

    @KafkaListener(id="event-group-1", topics = "${spring.kafka.template.default-topic}", groupId = "event-group")
    public void listen(ConsumerRecord<UUID, String> record) {

        final var event = gson.fromJson(record.value(), Event.class);

        log.info("M=ConsumerEvent -> key: {}, record: {}", record.key(), event);
    }

}
