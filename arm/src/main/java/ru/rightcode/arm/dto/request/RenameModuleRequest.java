package ru.rightcode.arm.dto.request;

import java.io.Serializable;

public record RenameModuleRequest(
        String newName
) implements Serializable {
}
