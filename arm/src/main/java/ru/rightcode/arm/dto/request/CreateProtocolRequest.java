package ru.rightcode.arm.dto.request;

import java.io.Serializable;

public record CreateProtocolRequest(
        String scalesResult,
        String result,
        String recommendations,
        String diagnosis
) implements Serializable {
}
