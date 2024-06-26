package br.com.portfolio.infra.customer.entity;

import br.com.portfolio.infra.converter.UUIDToStringConverter;
import br.com.portfolio.infra.shared.entity.AuditEntity;
import br.com.portfolio.infra.shared.entity.IEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
@Entity(name = "customerEntity")
public class CustomerEntity implements IEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "customer_seq")
    @SequenceGenerator(name = "customer_seq", sequenceName = "customer_seq", allocationSize = 1)
    private Long id;

    @Column(name = "tenant_id")
    @Convert(converter = UUIDToStringConverter.class)
    private UUID tenantId;

    @Column(name = "name", nullable = false)
    private String name;

    @Embedded
    private AddressVOEntity address;

    @Column(name = "active")
    private boolean active;

    @Column(name = "reward_points")
    private Long rewardPoints;

    @Embedded
    private AuditEntity audit;


}
