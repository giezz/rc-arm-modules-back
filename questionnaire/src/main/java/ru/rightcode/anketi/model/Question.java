package ru.rightcode.anketi.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.util.*;


@Getter
@Setter
@Entity
@Table(schema = "anketi")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "required", nullable = false)
    @Builder.Default
    private Boolean required = false;

    @OneToMany(mappedBy = "question")
    @ToString.Exclude
    @Builder.Default
    private Set<Variant> variants = new HashSet<>();

    @OneToMany(mappedBy = "question")
    @ToString.Exclude
    @Builder.Default
    private List<FormQuestion> formQuestions = new ArrayList<>();

    public Question(String content, Set<Variant> variants){
        this.content = content;
        this.variants = variants;
    }

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
