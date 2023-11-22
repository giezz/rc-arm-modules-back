package ru.rightcode.core.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Table(name = "_user")
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
            name = "user_role",
            joinColumns = @JoinColumn(name = "_user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "_role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;
}
