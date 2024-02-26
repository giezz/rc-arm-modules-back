package ru.rightcode.anketi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "doc")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Scale {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

//    @OneToMany(mappedBy = "scale")
//    @ToString.Exclude
//    @XmlElement(name = "form")
//    private List<Form> forms = new ArrayList<>();

    @OneToMany(mappedBy = "scale")
    @ToString.Exclude
    private List<Interpretation> interpretations = new ArrayList<>();

    public Scale(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Scale scale = (Scale) o;

        return getId().equals(scale.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
