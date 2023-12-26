package ru.rightcode.anketi.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(schema = "doc")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Answer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    // TODO: patient does not exist
    @ManyToOne
    @JoinColumn(
            name = "patient_id",
            referencedColumnName = "id"
    )
    @XmlTransient
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "variant_id")
    private Variant variant_id;

}
