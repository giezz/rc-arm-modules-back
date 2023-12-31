package ru.rightcode.arm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false, length = 18)
    private String phoneNumber;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    @JsonBackReference
    @ToString.Exclude
    private Set<Patient> patients;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "_user_id",
            referencedColumnName = "id"
    )
    @ToString.Exclude
    private User user;

}
