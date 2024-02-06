package ru.rightcode.anketi.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.rightcode.anketi.adapter.LocalDateAdapter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Table(schema = "doc")
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
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate birthDate;

    @Column(name = "death_date")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
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
    @XmlElement(name = "answer")
    private Set<Answer> answers = new HashSet<>();

    @OneToMany(mappedBy = "patient")
    @XmlElement(name = "formResult")
    private Set<FormResult> formResults = new LinkedHashSet<>();


    /*
    @ManyToOne
    @JoinColumn(name = "patient_status_id")
    private PatientStatus patientStatus;

    @ManyToOne
    @JoinColumn(
            name = "doctor_id",
            referencedColumnName = "id"
    )
    private Doctor doctor;

    @OneToOne
    @JoinColumn(name = "passport_id", referencedColumnName = "id", nullable = false)
    private Passport passport;
    */

}
