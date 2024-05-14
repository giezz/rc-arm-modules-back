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
@Table(schema = "medcarta")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@XmlType(
        name = "EpicrisisResponse",
        propOrder = {
                "id",
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

    @Column(name = "diagnosis_code", nullable = false)
    private Long diagnosisCode;

    @Column(name = "epicrisisi_data", nullable = false, length = -1)
    private String epicrisisiData;

    @Column(name = "creation_date", nullable = false)
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "hospitalization_history_id",
            referencedColumnName = "id"
    )
    @XmlTransient
    @JsonIgnore
    private HospitalizationHistory hospitalizationHistory;
}
