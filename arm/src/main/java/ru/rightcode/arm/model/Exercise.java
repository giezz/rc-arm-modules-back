package ru.rightcode.arm.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "exercise", schema = "doc")
@Getter
@Setter
@ToString
public class Exercise {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "video_url", nullable = false, length = 2083)
    private String videoUrl;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "exercise_type_id",
            referencedColumnName = "id"
    )
    @ToString.Exclude
    private ExerciseType exerciseType;

    public Exercise(Long id) {
        this.id = id;
    }

    public Exercise() {
    }
}
