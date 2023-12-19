package ru.rightcode.arm.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "exercise_type", schema = "doc")
@Getter
@Setter
@ToString
public class ExerciseType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;


}
