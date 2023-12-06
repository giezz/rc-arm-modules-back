package ru.rightcode.medcart.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Getter;
import lombok.Setter;
import ru.rightcode.medcart.adapter.LocalDateAdapter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(schema = "medcarta")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@XmlType(
        name = "PatientResponse",
        propOrder = {
                "id",
                "patientCode",
                "firstName",
                "middleName",
                "lastName",
                "birthDate",
                "deathDate",
                "address",
                "phoneNumber",
                "workPlaceData",
                "bookmark",
                "passport",
                "snils",
                "polis"
        })
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
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate birthDate;

    @Column(name = "death_date", nullable = true)
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "passport_id"
    )
    private Passport passport;

}
