package ru.rightcode.back.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "patient", schema = "doc", indexes = {
        @Index(name = "patient_code_uq", columnList = "patient_code", unique = true),
        @Index(name = "passport_uq", columnList = "passport_id", unique = true)
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "patient_code", nullable = false)
    private Long patientCode;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "death_date")
    private LocalDate deathDate;

    @Column(name = "address", nullable = false, length = -1)
    private String address;

    @Column(name = "phone_number", nullable = false, length = 18)
    private String phoneNumber;

    @Column(name = "work_place_data", nullable = false, length = -1)
    private String workPlaceData;

    @Column(name = "bookmark", length = -1)
    private String bookmark;

    @Column(name = "snils", nullable = false, length = 11)
    private String snils;

    @Column(name = "polis", nullable = false, length = 16)
    private String polis;

    @OneToMany(mappedBy = "patient")
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "patient")
    private List<FormResult> formResults = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Doctor doctor;
/*

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "patient_status_id", nullable = false)
    private PatientStatus patientStatus;
*/

/*    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "passport_id", nullable = false)
    private Passport passport;*/

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "_user_id"
    )
    @ToString.Exclude
    private User user;*/

}
