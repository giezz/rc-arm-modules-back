package ru.rightcode.arm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class FormQuestionId implements Serializable {
    private static final long serialVersionUID = -1275795703159616005L;
    @NotNull
    @Column(name = "id_form", nullable = false)
    private Long idForm;

    @NotNull
    @Column(name = "id_question", nullable = false)
    private Long idQuestion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FormQuestionId entity = (FormQuestionId) o;
        return Objects.equals(this.idForm, entity.idForm) &&
                Objects.equals(this.idQuestion, entity.idQuestion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idForm, idQuestion);
    }

}