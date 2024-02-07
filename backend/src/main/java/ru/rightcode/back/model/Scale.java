package ru.rightcode.back.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
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
