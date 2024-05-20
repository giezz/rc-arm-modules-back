package ru.rightcode.medcart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.rightcode.medcart.adapter.LocalDateAdapter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "rehabilitation_history", schema = "medcarta")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@XmlType(
        name = "RehabilitationHistoryResponse",
        propOrder = {
                "id",
                "muCode",
                "doctorCode",
                "creationDate",
                "rehabResult"
        })
public class RehabilitationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    @XmlTransient
    @JsonIgnore
    private Patient patient;

    @Column(name = "mu_code", nullable = false)
    private Long muCode;

    @Column(name = "doctor_code", nullable = false)
    private Long doctorCode;

    @Column(name = "creation_date", nullable = false)
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate creationDate;

    @Column(name = "rehab_result", nullable = false, length = Integer.MAX_VALUE)
    private String rehabResult;

    public RehabilitationHistory(Patient patient, Long muCode, Long doctorCode, LocalDate creationDate, String rehabResult) {
        this.patient = patient;
        this.muCode = muCode;
        this.doctorCode = doctorCode;
        this.creationDate = creationDate;
        this.rehabResult = rehabResult;
    }
}