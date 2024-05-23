package ru.rightcode.patient.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.proxy.HibernateProxy;
import ru.rightcode.patient.dto.response.form.QuestionTypeEnum;

import java.util.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "question", schema = "anketi")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "content", nullable = false, length = Integer.MAX_VALUE)
    private String content;

    @OneToMany(mappedBy = "question")
    private Set<Variant> variants = new HashSet<>();

    @OneToMany(mappedBy = "question")
    private List<FormQuestion> formQuestions = new ArrayList<>();

    @NotNull
    @ColumnDefault("false")
    @Column(name = "required", nullable = false)
    private Boolean required = false;

    @NotNull
    @Column(name = "type", nullable = false)
    private String type = QuestionTypeEnum.SINGLE_CHOICE.toString();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Question question = (Question) o;
        return getId() != null && Objects.equals(getId(), question.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
