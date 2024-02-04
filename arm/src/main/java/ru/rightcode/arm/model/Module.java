package ru.rightcode.arm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "module", schema = "doc")
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false, length = Integer.MAX_VALUE)
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rehab_program_id", nullable = false)
    @JsonBackReference
    private RehabProgram rehabProgram;

    @Column(name = "finished_at")
    private Instant finishedAt;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ModuleExercise> exercises = new ArrayList<>();

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ModuleForm> forms = new ArrayList<>();

    public void addExercise(ModuleExercise exercise) {
        exercises.add(exercise);
        exercise.setModule(this);
    }

    public void deleteExercise(ModuleExercise exercise) {
        exercises.remove(exercise);
        exercise.setModule(null);
    }

    public void addAllExercises(List<ModuleExercise> exercises) {
        for (ModuleExercise exercise : exercises) {
            this.exercises.add(exercise);
            exercise.setModule(this);
        }
    }

    public void addForm(ModuleForm form) {
        forms.add(form);
        form.setModule(this);
    }

    public void deleteForm(ModuleForm form) {
        forms.remove(form);
        form.setModule(null);
    }

    public void addAllForms(List<ModuleForm> forms) {
        for (ModuleForm form : forms) {
            this.forms.add(form);
            form.setModule(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Module module = (Module) o;

        return getId().equals(module.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}