package ru.rightcode.arm.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rightcode.arm.model.Module;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RehabProgramResponse {

    private Long id;
    private SimplePatientResponse patient;
    private SimpleDoctorResponse doctor;
    private Boolean isCurrent;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Module> modules;
}
