package ru.rightcode.arm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "interpretation", schema = "anketi")
public class Interpretation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "scale_id", nullable = false)
    private Scale scale;

    @NotNull
    @Column(name = "min_value", nullable = false, precision = 100, scale = 2)
    private BigDecimal minValue;

    @NotNull
    @Column(name = "max_value", nullable = false, precision = 100, scale = 2)
    private BigDecimal maxValue;

    @NotNull
    @Column(name = "description", nullable = false, length = Integer.MAX_VALUE)
    private String description;



}