package ru.rightcode.arm.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "module", schema = "doc")
@Getter
@Setter
@ToString
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "finished_at")
    private LocalDate finishedAt;

    @ManyToOne
    @JoinColumn(
            name = "rehab_program_id",
            referencedColumnName = "id"

    )
    private RehabProgram rehabProgram;
}
