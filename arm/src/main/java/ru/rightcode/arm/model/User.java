package ru.rightcode.arm.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "_user", schema = "doc")
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String username;

    @Column
    private String password;

    @ManyToMany
    @JoinTable(
            schema = "doc",
            name = "user_role",
            joinColumns = @JoinColumn(name = "_user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "_role_id", referencedColumnName = "id")
    )
    @ToString.Exclude
    private List<Role> roles = new ArrayList<>();

}
