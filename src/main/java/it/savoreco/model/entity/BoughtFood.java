package it.savoreco.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "bought_food")
public class BoughtFood {
    @EmbeddedId
    private BoughtFoodId id;

    @MapsId("purchaseId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "purchase_id", nullable = false)
    private Purchase purchase;

    @MapsId("foodId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    @Size(max = 128)
    @NotNull
    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "green_point", nullable = false)
    private Integer greenPoint;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "price", nullable = false, precision = 16, scale = 8)
    private BigDecimal price;

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "\"time\"", nullable = false)
    private Instant time;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "quantity", nullable = false)
    private Short quantity;

    public BoughtFoodId getId() {
        return id;
    }

    public void setId(BoughtFoodId id) {
        this.id = id;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGreenPoint() {
        return greenPoint;
    }

    public void setGreenPoint(Integer greenPoint) {
        this.greenPoint = greenPoint;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public Short getQuantity() {
        return quantity;
    }

    public void setQuantity(Short quantity) {
        this.quantity = quantity;
    }

}