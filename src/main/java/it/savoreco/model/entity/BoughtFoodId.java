package it.savoreco.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.util.Objects;

@Embeddable
public class BoughtFoodId implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 934405724345461243L;
    @NotNull
    @Column(name = "purchase_id", nullable = false)
    private Long purchaseId;

    @NotNull
    @Column(name = "food_id", nullable = false)
    private Integer foodId;

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
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
        BoughtFoodId entity = (BoughtFoodId) o;
        return Objects.equals(this.purchaseId, entity.purchaseId) &&
                Objects.equals(this.foodId, entity.foodId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseId, foodId);
    }

}