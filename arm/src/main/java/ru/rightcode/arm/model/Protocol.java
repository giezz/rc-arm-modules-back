package ru.rightcode.arm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "protocol", schema = "arm")
public class Protocol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rehab_program_id", nullable = false)
    private RehabProgram rehabProgram;

    @NotNull
    @Column(name = "creation_date", nullable = false)
    private Instant creationDate;

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

}
