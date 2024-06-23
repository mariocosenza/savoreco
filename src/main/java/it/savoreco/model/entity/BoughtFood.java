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
    @Id
    @Column(name = "purchase_id", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "purchase_id", nullable = false)
    private Purchase purchase = new Purchase();

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
    @ColumnDefault("1")
    @Column(name = "quantity", nullable = false)
    private Short quantity;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @ColumnDefault("1")
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant = new Restaurant();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
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


    public Short getQuantity() {
        return quantity;
    }

    public void setQuantity(Short quantity) {
        this.quantity = quantity;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }


    @Override
    public String toString() {
        return "BoughtFood{" +
                "id=" + id +
                ", purchase=" + purchase +
                ", name='" + name + '\'' +
                ", greenPoint=" + greenPoint +
                ", price=" + price +
                ", quantity=" + quantity +
                ", restaurant=" + restaurant +
                '}';
    }
}