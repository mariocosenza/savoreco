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
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id", nullable = false)
    private Integer id;

    @Size(max = 128)
    @NotNull
    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Address address;

    @ColumnDefault("")
    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Size(max = 1024)
    @Column(name = "image_object", length = 1024)
    private String imageObject;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "delivery_cost", nullable = false, precision = 16, scale = 8)
    private BigDecimal deliveryCost;

    @Size(max = 128)
    @Column(name = "category", length = 128)
    private String category;

    @NotNull
    @ColumnDefault("true")
    @Column(name = "deleted", nullable = false)
    private boolean deleted;

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "creation_time", nullable = false)
    private Instant creationTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageObject() {
        return imageObject;
    }

    public void setImageObject(String imageObject) {
        this.imageObject = imageObject;
    }

    public BigDecimal getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(BigDecimal deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Instant getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Instant creationTime) {
        this.creationTime = creationTime;
    }

}