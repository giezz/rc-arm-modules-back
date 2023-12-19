package ru.rightcode.arm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class ModuleExercisePk implements Serializable {

    @Column(name = "module_id")
    private Long moduleId;

    @Column(name = "id_exercise")
    private Long exerciseId;

    public ModuleExercisePk(Long moduleId, Long exerciseId) {
        this.moduleId = moduleId;
        this.exerciseId = exerciseId;
    }

    public ModuleExercisePk() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ModuleExercisePk that = (ModuleExercisePk) o;

        if (!getModuleId().equals(that.getModuleId())) return false;
        return getExerciseId().equals(that.getExerciseId());
    }

    @Override
    public int hashCode() {
        int result = getModuleId().hashCode();
        result = 31 * result + getExerciseId().hashCode();
        return result;
    }
}
