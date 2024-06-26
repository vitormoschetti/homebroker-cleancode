package br.com.portfolio.infra.shared.factory;

import br.com.portfolio.domain.shared.valueobject.AuditTimestamps;
import br.com.portfolio.infra.shared.entity.AuditEntity;
import org.springframework.stereotype.Component;

@Component
public class AuditFactory implements IFactory<AuditTimestamps, AuditEntity> {

    @Override
    public AuditTimestamps toModel(AuditEntity audit) {
        return new AuditTimestamps(audit.getCreatedAt(), audit.getUpdatedAt());
    }

    @Override
    public AuditEntity toEntity(AuditTimestamps auditTimestamps) {
        return new AuditEntity(auditTimestamps.getCreatedAt(), auditTimestamps.getUpdatedAt());
    }
}
