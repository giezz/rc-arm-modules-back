package ru.rightcode.patient.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ModuleExercise> moduleExercises = new LinkedHashSet<>();

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ModuleForm> moduleForms = new LinkedHashSet<>();

    public void addExercise(ModuleExercise exercise) {
        moduleExercises.add(exercise);
        exercise.setModule(this);
    }

    public void deleteExercise(ModuleExercise exercise) {
        moduleExercises.remove(exercise);
        exercise.setModule(null);
    }

    public void addForm(ModuleForm form) {
        moduleForms.add(form);
        form.setModule(this);
    }

    public void deleteForm(ModuleForm programForm) {
        moduleForms.remove(programForm);
        programForm.setModule(null);
    }

    public Set<ModuleExercise> getModuleExercises() {
        return moduleExercises.stream()
                .sorted(Comparator.comparing(moduleExercise -> moduleExercise.getBlock().getName()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public Set<ModuleExercise> getModuleExercisesSortedById() {
        return moduleExercises.stream()
                .sorted(Comparator.comparing(moduleExercise -> moduleExercise.getBlock().getId()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
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
