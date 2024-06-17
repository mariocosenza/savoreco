package it.savoreco.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;
import org.locationtech.jts.geom.Point;


@Entity
@Table(name = "address")
public class Address {
    @EmbeddedId
    private AddressId id;

    @Size(max = 512)
    @Column(name = "city", length = 512)
    private String city;

    @Size(max = 2)
    @Column(name = "country_code", length = 2)
    @ColumnDefault("IT")
    private String countryCode;


    @Column(name = "gps_point")
    private Point gpsPoint;

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

    public Point getGpsPoint() {
        return gpsPoint;
    }

    public void setGpsPoint(Point gpsPoint) {
        this.gpsPoint = gpsPoint;
    }

}