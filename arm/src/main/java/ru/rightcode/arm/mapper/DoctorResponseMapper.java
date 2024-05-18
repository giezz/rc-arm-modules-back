package ru.rightcode.arm.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rightcode.arm.dto.response.DoctorResponse;
import ru.rightcode.arm.model.Doctor;

@Component
@RequiredArgsConstructor
public class DoctorResponseMapper implements Mapper<Doctor, DoctorResponse> {


    @Override
    public DoctorResponse map(Doctor object) {
        return new DoctorResponse(
                object.getId(),
                object.getFirstName(),
                object.getMiddleName(),
                object.getLastName()
        );
    }
}
