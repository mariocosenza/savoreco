package it.savoreco.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant = new Restaurant();

    @Size(max = 1024)
    @Column(name = "image_object", length = 1024)
    private String imageObject;

    @Size(max = 128)
    @NotNull
    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @ColumnDefault("")
    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @NotNull
    @ColumnDefault("true")
    @Column(name = "available", nullable = false)
    private boolean available;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "green_point", nullable = false)
    private Integer greenPoint;

    @Size(max = 128)
    @NotNull
    @ColumnDefault("other")
    @Column(name = "category", nullable = false, length = 128)
    private String category;

    @Column(name = "allergens", length = Integer.MAX_VALUE)
    private String allergens;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "quantity", nullable = false)
    private Short quantity;

    @NotNull
    @ColumnDefault("10")
    @Column(name = "price", nullable = false)
    private Double price;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getImageObject() {
        return imageObject;
    }

    public void setImageObject(String imageObject) {
        this.imageObject = imageObject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Integer getGreenPoint() {
        return greenPoint;
    }

    public void setGreenPoint(Integer greenPoint) {
        this.greenPoint = greenPoint;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAllergens() {
        return allergens;
    }

    public void setAllergens(String allergens) {
        this.allergens = allergens;
    }

    public Short getQuantity() {
        return quantity;
    }

    public void setQuantity(Short quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", restaurant=" + restaurant +
                ", imageObject='" + imageObject + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", available=" + available +
                ", greenPoint=" + greenPoint +
                ", category='" + category + '\'' +
                ", allergens='" + allergens + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}