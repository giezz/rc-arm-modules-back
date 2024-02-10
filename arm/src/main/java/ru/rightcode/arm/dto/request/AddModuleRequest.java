package ru.rightcode.arm.dto.request;

import java.io.Serializable;

public record AddModuleRequest (
        String name
) implements Serializable {
}
