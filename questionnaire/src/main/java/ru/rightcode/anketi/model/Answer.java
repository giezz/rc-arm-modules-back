package ru.rightcode.anketi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(schema = "doc")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    // TODO: patient does not exist
    @ManyToOne
    @JoinColumn(
            name = "patient_id",
            referencedColumnName = "id"
    )
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "variant_id")
    private Variant variant;

    @NotNull
    @Column(name = "answered_at", nullable = false)
    private Instant answeredAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer = (Answer) o;

        return getId().equals(answer.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
