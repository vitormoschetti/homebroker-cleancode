package br.com.portfolio.infra.portfolio.entity;

import br.com.portfolio.infra.shared.entity.AuditEntity;
import br.com.portfolio.infra.shared.entity.IEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "portfolio_item")
@Entity(name = "portfolioItemEntity")
public class PortfolioItemEntity implements IEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "portfolio_item_seq")
    @SequenceGenerator(name = "portfolio_item_seq", sequenceName = "portfolio_item_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name="portfolio_id", nullable=false)
    private PortfolioEntity portfolio;

    @Embedded
    private AssetPositionVOEntity position;

    @Embedded
    private AuditEntity audit;


}
