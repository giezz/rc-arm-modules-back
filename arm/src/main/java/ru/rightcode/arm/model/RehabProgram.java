package ru.rightcode.arm.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "rehab_program", schema = "doc")
@Getter
@Setter
@ToString
public class RehabProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "patient_id",
            referencedColumnName = "id"
    )
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "doctor_id",
            referencedColumnName = "id"
    )
    private Doctor doctor;

    @Column(name = "is_current", nullable = false)
    private Boolean isCurrent;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @OneToMany(mappedBy = "rehabProgram")
    private Set<Module> modules;

}
