package ru.rightcode.formback.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@Table(schema = "doc")
//@ToString(exclude = {"scale", "questions"})
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
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scale_id")
    private Scale scale;

    @OneToMany(mappedBy = "form")
    @ToString.Exclude
    private Set<FormResult> formResults = new LinkedHashSet<>();


    @OneToMany(mappedBy = "idForm")
    @ToString.Exclude
    private Set<FormQuestion> formQuestions = new LinkedHashSet<>();

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
