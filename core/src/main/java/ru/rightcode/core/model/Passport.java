package ru.rightcode.core.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Passport {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "series", nullable = false, length = 4)
    private String series;
    @Basic
    @Column(name = "number", nullable = false, length = 6)
    private String number;
    @Basic
    @Column(name = "issued", nullable = false, length = -1)
    private String issued;
    @Basic
    @Column(name = "issued_date", nullable = false)
    private LocalDate issuedDate;

}
