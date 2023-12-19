package ru.rightcode.arm.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(schema = "doc", name = "module_exercise")
@Getter
@Setter
@ToString
public class ModuleExercise {

    @EmbeddedId
    private ModuleExercisePk moduleExercisePk;

    @ManyToOne
    @JoinColumn(
            name = "block_id",
            referencedColumnName = "id"
    )
    private Block block;
}
