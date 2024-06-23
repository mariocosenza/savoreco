package it.savoreco.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private UserAccount user = new UserAccount();

    @NotNull
    @ColumnDefault("0")
    @Column(name = "delivery_cost", nullable = false, precision = 16, scale = 8)
    private BigDecimal deliveryCost;

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "\"time\"", nullable = false)
    private Instant time;

    @ColumnDefault("10")
    @Column(name = "iva")
    private Short iva;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "total_cost", nullable = false, precision = 16, scale = 8)
    private BigDecimal totalCost;
    @NotNull
    @ColumnDefault("false")
    @Column(name = "pick_up", nullable = false)
    private boolean pickUp;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "street", referencedColumnName = "street"),
            @JoinColumn(name = "zipcode", referencedColumnName = "zipcode")
    })
    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserAccount getUser() {
        return user;
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }

    public BigDecimal getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(BigDecimal deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public Short getIva() {
        return iva;
    }

    public void setIva(Short iva) {
        this.iva = iva;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public boolean getPickUp() {
        return pickUp;
    }

    public void setPickUp(boolean pickUp) {
        this.pickUp = pickUp;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Enumerated
    @ColumnDefault("pending")
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "status", columnDefinition = "order_status not null")
    private Statuses status;

    @Enumerated
    @ColumnDefault("google")
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "payment_method", columnDefinition = "payment_type not null")
    private PaymentMethods paymentMethod;

    public boolean isPickUp() {
        return pickUp;
    }

    public Statuses getStatus() {
        return status;
    }

    public void setStatus(Statuses status) {
        this.status = status;
    }

    public PaymentMethods getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethods paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public enum Statuses {
        delivered,
        pending,
        payed,
        confirmed,
        delivering,
        canceled
    }

    public enum PaymentMethods {
        google,
        paypal,
        visa,
        mastercard
    }
}