package ru.rightcode.medcart.model;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import ru.rightcode.medcart.adapter.LocalDateAdapter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(schema = "medcarta")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@XmlType(
        name = "EpicrisisResponse",
        propOrder = {
                "id",
                "muCode",
                "doctorCode",
                "diagnosisCode",
                "epicrisisiData",
                "creationDate"
        }
)
public class Epicrisis {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "mu_code", nullable = false)
    private Long muCode;

    @Column(name = "doctor_code", nullable = false)
    private Long doctorCode;

    @Column(name = "diagnosis_code", nullable = false)
    private Long diagnosisCode;

    @Column(name = "epicrisisi_data", nullable = false, length = -1)
    private String epicrisisiData;

    @Column(name = "creation_date", nullable = false)
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate creationDate;

    @ManyToOne
    @JoinColumn(
            name = "patient_id",
            referencedColumnName = "id"
    )
    @XmlTransient
    private Patient patient;
}
