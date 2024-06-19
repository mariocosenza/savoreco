package it.savoreco.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "basket_contains")
public class BasketContain {
    @EmbeddedId
    private BasketContainId id = new BasketContainId();

    @MapsId("basketId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "basket_id", nullable = false)
    private Basket basket;

    @MapsId("foodId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    public BasketContainId getId() {
        return id;
    }

    public void setId(BasketContainId id) {
        this.id = id;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

}