package it.savoreco.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private UserAccount user;

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

/*
 TODO [Reverse Engineering] create field to map the 'status' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @ColumnDefault("pending")
    @Column(name = "status", columnDefinition = "order_status not null")
    private Object status;
*/
/*
 TODO [Reverse Engineering] create field to map the 'payment_method' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @ColumnDefault("google")
    @Column(name = "payment_method", columnDefinition = "payment_type not null")
    private Object paymentMethod;
*/
}