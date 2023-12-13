package ru.rightcode.arm.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimpleRehabProgramResponse {

    private Long id;
    private Boolean isCurrent;
    private LocalDate startDate;
    private LocalDate endDate;
}
