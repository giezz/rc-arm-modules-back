package ru.rightcode.anketi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Table(schema = "doc")
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
    private List<Variant> variants = new ArrayList<>();

    @OneToMany(mappedBy = "idQuestion")
    @ToString.Exclude
    private List<FormQuestion> formQuestions = new ArrayList<>();


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
