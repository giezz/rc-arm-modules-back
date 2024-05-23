package ru.rightcode.anketi.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@Getter
@Setter
@Entity
@Table(schema = "anketi")
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
    @Builder.Default
    private List<FormQuestion> formQuestions = new ArrayList<>();

    public Form(Long id, String name, String description, Scale scale) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.scale = scale;
    }

    @Override
    public int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Form that = (Form) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }
}
