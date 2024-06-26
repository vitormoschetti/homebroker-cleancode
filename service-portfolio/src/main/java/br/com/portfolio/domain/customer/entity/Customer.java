package br.com.portfolio.domain.customer.entity;

import br.com.portfolio.domain.customer.valueobject.AddressVO;
import br.com.portfolio.domain.shared.entity.BaseEntity;
import br.com.portfolio.domain.shared.entity.IAggregateRoot;
import br.com.portfolio.domain.shared.notification.DomainNotification;
import br.com.portfolio.domain.shared.notification.DomainNotificationError;
import br.com.portfolio.domain.shared.valueobject.AuditTimestamps;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Customer extends BaseEntity implements IAggregateRoot {

    // Todo id and tenantId?
    private Long id;
    private UUID tenantId;
    private String name;
    private AddressVO address;
    private Boolean active;
    private Long rewardPoints;
    private final AuditTimestamps audit;

    public Customer() {
        super(new DomainNotification());
        this.audit = new AuditTimestamps();
    }

    public Customer(Long id, UUID tenantId, String name, AddressVO address, boolean active, Long rewardPoints, AuditTimestamps audit) {
        super(new DomainNotification());
        this.id = id;
        this.tenantId = tenantId;
        this.name = name;
        this.address = address;
        this.active = active;
        this.rewardPoints = rewardPoints;
        this.audit = audit;
    }

    public void create(final String name, final String street, final String city, final String state, final String zipCode) {
        this.tenantId = UUID.randomUUID();
        this.name = name;
        this.address = new AddressVO(street, city, state, zipCode);
        this.rewardPoints = 0L;
        this.activate();
        this.validate();
    }

    public void changeName(final String name) {
        this.name = name;
        this.audit.updateNow();
        this.validate();
    }

    public void changeAddress(final String street, final String state, final String city, final String zipCode) {
        this.address = new AddressVO(street, city, state, zipCode);
        this.audit.updateNow();
        this.validate();
    }

    public void changeAll(final String name, final String street, final String state, final String city, final String zipCode) {
        this.changeName(name);
        this.changeAddress(street, state, city, zipCode);
        this.audit.updateNow();
    }

    public void activate() {
        this.active = Boolean.TRUE;
        this.audit.updateNow();
    }

    public void deactivate() {
        this.active = Boolean.FALSE;
        this.audit.updateNow();
    }

    public void addRewardPoints(final Long points) {
        if (points < 0)
            this.addMessage(new DomainNotificationError("Reward Points must be greater equal zero"));

        this.rewardPoints += points;
        this.audit.updateNow();
    }

    @Override
    protected void validate() {
        if (Objects.isNull(this.name) || this.name.isEmpty())
            this.addMessage(new DomainNotificationError("Name is required"));
        if (this.address.hasErrors())
            this.address.getMessages().forEach(this::addMessage);
    }

    public Boolean isActive() {
        return this.active;
    }

    public Long getId() {
        return this.id;
    }

    public UUID getTenantId() {
        return tenantId;
    }

    public Long getRewardPoints() {
        return rewardPoints;
    }

    public String getName() {
        return this.name;
    }

    public AddressVO getAddress() {
        return this.address;
    }

    public AuditTimestamps getAudit() {
        return audit;
    }

    public Instant getCreatedAt() {
        return this.audit.getCreatedAt();
    }

    public Instant getUpdatedAt() {
        return this.audit.getUpdatedAt();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", active=" + active +
                ", rewardPoints=" + rewardPoints +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(tenantId, customer.tenantId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(tenantId);
    }
}
