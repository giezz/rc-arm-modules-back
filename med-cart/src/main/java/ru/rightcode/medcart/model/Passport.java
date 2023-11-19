package ru.rightcode.medcart.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;

import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Getter;
import lombok.Setter;
import ru.rightcode.medcart.adapter.LocalDateAdapter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@XmlType(
        name = "PassportResponse",
        propOrder = {
                "id",
                "series",
                "number",
                "issued",
                "issuedDate"
        })
public class Passport {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "series", nullable = false, length = 4)
    private String series;

    @Column(name = "number", nullable = false, length = 6)
    private String number;

    @Column(name = "issued", nullable = false, length = -1)
    private String issued;

    @Column(name = "issued_date", nullable = false)
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate issuedDate;

}
