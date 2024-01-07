package ru.rightcode.anketi.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@Table(schema = "doc")
//@ToString(exclude = {"scale", "questions"})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@NoArgsConstructor
@AllArgsConstructor
public class Form {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scale_id")
    private Scale scale ;

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "form_question",
            joinColumns = @JoinColumn(name = "id_form"),
            inverseJoinColumns = @JoinColumn(name = "id_question")
    )
    private Set<Question> questions = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Form form = (Form) o;

        return getId().equals(form.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
