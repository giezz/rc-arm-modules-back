package ru.rightcode.arm.dto.request;

import java.io.Serializable;

public record CreateProtocolRequest(
        String modulesFormsResults,
        String programFormsResults,
        String result,
        String recommendations,
        String diagnosis,
        Boolean isFinal
) implements Serializable {
}
