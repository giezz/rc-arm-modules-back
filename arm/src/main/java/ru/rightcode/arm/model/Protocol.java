package ru.rightcode.arm.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "protocol", schema = "doc")
public class Protocol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull
    @Column(name = "is_final", nullable = false)
    private Boolean isFinal = false;

    @NotNull
    @Column(name = "rehab_result", nullable = false, length = Integer.MAX_VALUE)
    private String rehabResult;

    @NotNull
    @Column(name = "recommendations", nullable = false, length = Integer.MAX_VALUE)
    private String recommendations;

    @NotNull
    @Column(name = "rehab_diagnosis", nullable = false, length = Integer.MAX_VALUE)
    private String rehabDiagnosis;

    @NotNull
    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rehab_program_id", nullable = false)
    private RehabProgram rehabProgram;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

}