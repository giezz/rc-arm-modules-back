package ru.rightcode.anketi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
    private Set<Variant> variants = new HashSet<>();

    @OneToMany(mappedBy = "idQuestion")
    @ToString.Exclude
    private List<FormQuestion> formQuestions = new ArrayList<>();

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
