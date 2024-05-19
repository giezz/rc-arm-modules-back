package ru.rightcode.patient.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "passport", schema = "arm")
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 4)
    @NotNull
    @Column(name = "series", nullable = false, length = 4)
    private String series;

    @Size(max = 6)
    @NotNull
    @Column(name = "number", nullable = false, length = 6)
    private String number;

    @NotNull
    @Column(name = "issued_date", nullable = false)
    private Instant issuedDate;

    @NotNull
    @Column(name = "issued", nullable = false, length = Integer.MAX_VALUE)
    private String issued;

}