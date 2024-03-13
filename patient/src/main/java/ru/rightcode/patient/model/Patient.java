package ru.rightcode.patient.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "patient", schema = "arm")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "patient_code", nullable = false)
    private Long patientCode;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "_user_id", nullable = false)
    private User user;

    @Size(max = 255)
    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Size(max = 255)
    @Column(name = "middle_name")
    private String middleName;

    @Size(max = 255)
    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Size(max = 18)
    @NotNull
    @Column(name = "phone_number", nullable = false, length = 18)
    private String phoneNumber;

    @Size(max = 11)
    @NotNull
    @Column(name = "snils", nullable = false, length = 11)
    private String snils;

    @Size(max = 16)
    @NotNull
    @Column(name = "polis", nullable = false, length = 16)
    private String polis;

    @NotNull
    @Column(name = "gender", nullable = false, length = Integer.MAX_VALUE)
    private String gender;

    @Column(name = "death_date")
    private LocalDate deathDate;

    @NotNull
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @NotNull
    @Column(name = "address", nullable = false, length = Integer.MAX_VALUE)
    private String address;

    @Column(name = "bookmark", length = Integer.MAX_VALUE)
    private String bookmark;

    @NotNull
    @Column(name = "work_place_data", nullable = false, length = Integer.MAX_VALUE)
    private String workPlaceData;

}