package ru.rightcode.anketi.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.util.HashSet;
import java.util.Set;



@Getter
@Setter
@Entity
//@RequiredArgsConstructor
//@ToString(exclude = {"forms", "variants"})
@Table(schema = "doc")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
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

    @OneToMany(mappedBy = "question_id")
    @ToString.Exclude
    @Builder.Default
    private Set<Variant> variants = new HashSet<>();

    @Builder.Default
    @ManyToMany(mappedBy = "questions")
    @ToString.Exclude
    private Set<Form> forms = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        return getId().equals(question.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
