package br.com.log.application.event;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    private String eventName;
    private UUID traceId;
    private Instant instantCreated;
    private Object payload;

}
