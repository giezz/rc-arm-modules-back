package ru.rightcode.anketi.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(schema = "doc")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Variant {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    // numeric(100,2)
    @Column(name = "score", nullable = false)
    private Double score;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question_id;

    @OneToMany(mappedBy = "variant")
    private Set<Answer> answers = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Variant variant = (Variant) o;

        return getId().equals(variant.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
