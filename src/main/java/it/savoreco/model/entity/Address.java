package it.savoreco.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "address")
public class Address {
    @EmbeddedId
    private AddressId id;

    @Size(max = 512)
    @Column(name = "city", length = 512)
    private String city;

    @Size(max = 2)
    @NotNull
    @ColumnDefault("IT")
    @Column(name = "country_code", nullable = false, length = 2)
    private String countryCode;

    @ColumnDefault("0")
    @Column(name = "lat")
    private Double lat;

    @ColumnDefault("0")
    @Column(name = "lon")
    private Double lon;

    public AddressId getId() {
        return id;
    }

    public void setId(AddressId id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}