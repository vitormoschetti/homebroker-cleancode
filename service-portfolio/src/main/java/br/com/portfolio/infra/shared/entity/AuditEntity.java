package br.com.portfolio.infra.shared.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class AuditEntity implements IEntity {

    @Column(nullable = false)
    private Instant createdAt;

    private Instant updatedAt;

}
