package ru.rightcode.arm.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "program_module", schema = "doc")
@Getter
@Setter
@ToString
public class ProgramModule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "finished_at")
    private LocalDate finishedAt;

    @ManyToOne
    @JoinColumn(
            name = "module_id",
            referencedColumnName = "id"
    )
    private Module module;

    @ManyToOne
    @JoinColumn(
            name = "rehab_program_id",
            referencedColumnName = "id"
    )
    private RehabProgram rehabProgram;
}
