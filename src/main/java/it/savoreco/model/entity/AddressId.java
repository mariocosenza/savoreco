package it.savoreco.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class AddressId implements java.io.Serializable {
    private static final long serialVersionUID = 946913276673592358L;
    @Size(max = 256)
    @NotNull
    @Column(name = "street", nullable = false, length = 256)
    private String street;

    @Size(max = 16)
    @NotNull
    @Column(name = "zipcode", nullable = false, length = 16)
    private String zipcode;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AddressId entity = (AddressId) o;
        return Objects.equals(this.zipcode, entity.zipcode) &&
                Objects.equals(this.street, entity.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipcode, street);
    }

}