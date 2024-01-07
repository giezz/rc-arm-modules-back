package ru.rightcode.arm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "patient", schema = "doc", indexes = {
        @Index(name = "patient_code_uq", columnList = "patient_code", unique = true),
        @Index(name = "passport_uq", columnList = "passport_id", unique = true)
})
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "patient_code", nullable = false)
    private Long patientCode;

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

    @NotNull
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "death_date")
    private LocalDate deathDate;

    @NotNull
    @Column(name = "address", nullable = false, length = Integer.MAX_VALUE)
    private String address;

    @Size(max = 18)
    @NotNull
    @Column(name = "phone_number", nullable = false, length = 18)
    private String phoneNumber;

    @NotNull
    @Column(name = "work_place_data", nullable = false, length = Integer.MAX_VALUE)
    private String workPlaceData;

    @Column(name = "bookmark", length = Integer.MAX_VALUE)
    private String bookmark;

    @Size(max = 11)
    @NotNull
    @Column(name = "snils", nullable = false, length = 11)
    private String snils;

    @Size(max = 16)
    @NotNull
    @Column(name = "polis", nullable = false, length = 16)
    private String polis;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "patient_status_id", nullable = false)
    private PatientStatus patientStatus;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "passport_id", nullable = false)
    private Passport passport;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Patient patient = (Patient) o;

        return getId().equals(patient.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
