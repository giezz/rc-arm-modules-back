package ru.rightcode.anketi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
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
    @EqualsAndHashCode.Exclude
    private Set<FormResult> formResults = new LinkedHashSet<>();


    @OneToMany(mappedBy = "idForm")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FormQuestion> formQuestions = new ArrayList<>();

    public Form(Long id, String name, String description, Scale scale){
        this.id = id;
        this.name = name;
        this.description = description;
        this.scale = scale;
    }
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
