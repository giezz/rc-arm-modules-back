package ru.rightcode.arm.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(schema = "doc")
@Getter
@Setter
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
    private LocalDate issuedDate;

}
