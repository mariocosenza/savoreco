package it.savoreco.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "user_account")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Size(max = 128)
    @NotNull
    @Column(name = "email", nullable = false, length = 128)
    private String email;

    @Size(max = 48)
    @NotNull
    @Column(name = "name", nullable = false, length = 48)
    private String name;

    @Size(max = 48)
    @NotNull
    @Column(name = "surname", nullable = false, length = 48)
    private String surname;

    @Size(max = 512)
    @NotNull
    @Column(name = "password", nullable = false, length = 512)
    private String password;

    @NotNull
    @Column(name = "age", nullable = false)
    private LocalDate age;

    @NotNull
    @ColumnDefault("false")
    @Column(name = "deleted", nullable = false)
    private boolean deleted;

    @Column(name = "expires")
    private Instant expires;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "street", referencedColumnName = "street"),
            @JoinColumn(name = "zipcode", referencedColumnName = "zipcode")
    })
    private Address address = new Address();

    @Size(max = 2)
    @ColumnDefault("IT")
    @Column(name = "country_code", length = 2)
    private String countryCode;

    @Size(max = 1024)
    @Column(name = "avatar_image", length = 1024)
    private String avatarImage;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "eco_point", nullable = false)
    private Integer ecoPoint;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getAge() {
        return age;
    }

    public void setAge(LocalDate age) {
        this.age = age;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Instant getExpires() {
        return expires;
    }

    public void setExpires(Instant expires) {
        this.expires = expires;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(String avatarImage) {
        this.avatarImage = avatarImage;
    }

    public Integer getEcoPoint() {
        return ecoPoint;
    }

    public void setEcoPoint(Integer ecoPoint) {
        this.ecoPoint = ecoPoint;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", deleted=" + deleted +
                ", expires=" + expires +
                ", address=" + address +
                ", countryCode='" + countryCode + '\'' +
                ", avatarImage='" + avatarImage + '\'' +
                ", ecoPoint=" + ecoPoint +
                '}';
    }
}