package ru.rightcode.core.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Table(schema = "doc")
@Getter
@Setter
@ToString
public class Doctor {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "doctor_code", nullable = false)
    private Long doctorCode;

    @Column(name = "first_name", nullable = false, length = 255)
    private String firstName;

    @Column(name = "middle_name", nullable = true, length = 255)
    private String middleName;

    @Column(name = "last_name", nullable = false, length = 255)
    private String lastName;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "phone_number", nullable = false, length = 18)
    private String phoneNumber;

    @ManyToMany
    @JoinTable(
            schema = "doc",
            name = "doctor_patient",
            joinColumns = @JoinColumn(name = "doctor_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id")
    )
    private Set<Patient> patients;

    @ManyToOne
    @JoinColumn(
            name = "_user_id",
            referencedColumnName = "id"
    )
    private User user;

}
