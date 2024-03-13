package ru.rightcode.arm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "module", schema = "arm")
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rehab_program_id", nullable = false)
    private RehabProgram rehabProgram;

    @Column(name = "finished_at")
    private Instant finishedAt;

    @NotNull
    @Column(name = "name", nullable = false, length = Integer.MAX_VALUE)
    private String name;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ModuleExercise> exercises = new ArrayList<>();

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ModuleForm> forms = new ArrayList<>();

    public void addExercise(ModuleExercise exercise) {
        exercises.add(exercise);
        exercise.setModule(this);
    }

    public void deleteExercise(ModuleExercise exercise) {
        exercises.remove(exercise);
        exercise.setModule(null);
    }

    public void addForm(ModuleForm form) {
        forms.add(form);
        form.setModule(this);
    }

    public void deleteForm(ModuleForm programForm) {
        forms.remove(programForm);
        programForm.setModule(null);
    }

}
