package ru.rightcode.arm.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.dto.response.PatientResponse;
import ru.rightcode.arm.dto.response.RehabProgramResponse;
import ru.rightcode.arm.model.Patient;
import ru.rightcode.arm.model.PatientStatus;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PatientResponseMapper implements Mapper<Patient, PatientResponse> {

    @Override
    public PatientResponse map(Patient object) {
        PatientStatus status = Optional.of(object.getPatientStatus()).orElse(null);
        return new PatientResponse(
                object.getId(),
                object.getPatientCode(),
                object.getFirstName(),
                object.getMiddleName(),
                object.getLastName(),
                object.getBirthDate(),
                object.getDeathDate(),
                object.getAddress(),
                object.getPhoneNumber(),
                object.getWorkPlaceData(),
                object.getSnils(),
                object.getPolis(),
                status,
                null
        );
    }

    public PatientResponse mapWithAllData(Patient object) {
        PatientStatus status = Optional.of(object.getPatientStatus()).orElse(null);
        return new PatientResponse(
                object.getId(),
                object.getPatientCode(),
                object.getFirstName(),
                object.getMiddleName(),
                object.getLastName(),
                object.getBirthDate(),
                object.getDeathDate(),
                object.getAddress(),
                object.getPhoneNumber(),
                object.getWorkPlaceData(),
                object.getSnils(),
                object.getPolis(),
                status,
                object.getPassport()
        );
    }
}
