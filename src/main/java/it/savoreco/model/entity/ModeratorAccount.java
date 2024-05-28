package it.savoreco.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "moderator_account")
public class ModeratorAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "moderator_id", nullable = false)
    private Integer id;

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

}