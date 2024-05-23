package ru.rightcode.patient.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "_user", schema = "arm")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "login", nullable = false)
    private String username;

    @Size(max = 255)
    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(
            schema = "arm",
            name = "user_role",
            joinColumns = @JoinColumn(name = "_user_id"),
            inverseJoinColumns = @JoinColumn(name = "_role_id"))
    private Set<Role> roles = new LinkedHashSet<>();

}