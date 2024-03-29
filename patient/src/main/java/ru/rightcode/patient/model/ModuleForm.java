package ru.rightcode.patient.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "module_form", schema = "arm")
public class ModuleForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "finished_at")
    private Instant finishedAt;

    @Column(name = "score", precision = 100, scale = 2)
    private BigDecimal score;

}