package ru.rightcode.medcart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "hospitalization_history", schema = "medcarta")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@XmlType(
        name = "HospitalizationHistoryResponse",
        propOrder = {
                "id",
                "muCode",
                "startDate",
                "endDate",
                "doctorCode",
                "hospData"
        })
public class HospitalizationHistory {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "mu_code", nullable = false)
    private Long muCode;

    @Column(name = "start_date", nullable = false)
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate endDate;

    @Column(name = "doctor_code", nullable = false)
    private Long doctorCode;

    @Column(name = "hosp_data", nullable = false, length = -1)
    private String hospData;

    @ManyToOne
    @JoinColumn(
            name = "patient_id",
            referencedColumnName = "id"
    )
    @XmlTransient
    @JsonIgnore
    private Patient patient;
}
