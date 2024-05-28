package it.savoreco.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.util.Objects;

@Embeddable
public class BasketContainId implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = -8764401838796867261L;
    @NotNull
    @Column(name = "basket_id", nullable = false)
    private Long basketId;

    @NotNull
    @Column(name = "food_id", nullable = false)
    private Integer foodId;

    public Long getBasketId() {
        return basketId;
    }

    public void setBasketId(Long basketId) {
        this.basketId = basketId;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BasketContainId entity = (BasketContainId) o;
        return Objects.equals(this.basketId, entity.basketId) &&
                Objects.equals(this.foodId, entity.foodId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basketId, foodId);
    }

}