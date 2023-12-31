package ru.rightcode.arm.model;

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
@Table(name = "rehab_program_log")
public class RehabProgramLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "rehab_program_id", nullable = false)
    private RehabProgram rehabProgram;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "who_changed", nullable = false)
    private Doctor whoChanged;

    @NotNull
    @Column(name = "change_date", nullable = false)
    private LocalDate changeDate;

    @NotNull
    @Column(name = "change", nullable = false, length = Integer.MAX_VALUE)
    private String change;

    @NotNull
    @Column(name = "operation", nullable = false, length = Integer.MAX_VALUE)
    private String operation;



}