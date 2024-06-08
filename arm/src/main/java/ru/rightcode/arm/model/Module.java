package ru.rightcode.arm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public Module(String name) {
        this.name = name;
    }

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

    public void setExercises(List<ModuleExercise> exercises) {
        this.exercises.clear();
        this.exercises.addAll(exercises);
        exercises.forEach(exercise -> exercise.setModule(this));
    }

    public void setForms(List<ModuleForm> forms) {
        this.forms.clear();
        this.forms.addAll(forms);
        forms.forEach(form -> form.setModule(this));
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Module module = (Module) o;
        return getId() != null && Objects.equals(getId(), module.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
