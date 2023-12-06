package ru.rightcode.core.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Table(schema = "doc")
@Getter
@Setter
public class Patient {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "patient_code", nullable = false)
    private Long patientCode;

    @Column(name = "first_name", nullable = false, length = 255)
    private String firstName;

    @Column(name = "middle_name", nullable = true, length = 255)
    private String middleName;

    @Column(name = "last_name", nullable = false, length = 255)
    private String lastName;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "death_date", nullable = true)
    private LocalDate deathDate;

    @Column(name = "address", nullable = false, length = -1)
    private String address;

    @Column(name = "phone_number", nullable = false, length = 18)
    private String phoneNumber;

    @Column(name = "work_place_data", nullable = false, length = -1)
    private String workPlaceData;

    @Column(name = "bookmark", nullable = true, length = -1)
    private String bookmark;

    @Column(name = "snils", nullable = false, length = 11)
    private String snils;

    @Column(name = "polis", nullable = false, length = 16)
    private String polis;

    @ManyToOne
    @JoinColumn(name = "patient_status_id")
    private PatientStatus patientStatus;

    @OneToOne
    @JoinColumn(name = "passport_id", referencedColumnName = "id", nullable = false)
    private Passport passport;

}
