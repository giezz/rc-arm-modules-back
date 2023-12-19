package ru.rightcode.arm.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "module_form", schema = "doc")
@Getter
@Setter
@ToString
public class ModuleForm {

    @EmbeddedId
    private ModuleFormPk moduleFormPk;

    @ManyToOne
    @JoinColumn(
            name = "block_id",
            referencedColumnName = "id"
    )
    private Block block;
}
