package ru.rightcode.arm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class ModuleFormPk implements Serializable {

    @Column(name = "module_id")
    private Long moduleId;

    @Column(name = "form_id")
    private Long formId;

    public ModuleFormPk(Long moduleId, Long formId) {
        this.moduleId = moduleId;
        this.formId = formId;
    }

    public ModuleFormPk() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ModuleFormPk that = (ModuleFormPk) o;

        if (!getModuleId().equals(that.getModuleId())) return false;
        return getFormId().equals(that.getFormId());
    }

    @Override
    public int hashCode() {
        int result = getModuleId().hashCode();
        result = 31 * result + getFormId().hashCode();
        return result;
    }
}
