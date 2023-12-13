package ru.rightcode.arm.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rightcode.arm.model.Doctor;
import ru.rightcode.arm.model.Patient;
import ru.rightcode.arm.model.RehabProgram;
import ru.rightcode.arm.repository.RehabProgramRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RehabProgramService {

    private final RehabProgramRepository rehabProgramRepository;

    public void create(Doctor doctor, Patient patient) {
        RehabProgram rehabProgram = new RehabProgram();

        rehabProgram.setDoctor(doctor);
        rehabProgram.setPatient(patient);
        rehabProgram.setIsCurrent(true);
        rehabProgram.setStartDate(LocalDate.now());

        rehabProgramRepository.save(rehabProgram);

    }

    public RehabProgram get(Doctor doctor, Patient patient) {
        return rehabProgramRepository.getProgramByDoctorAndPatient(doctor, patient)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<RehabProgram> getAll(Doctor doctor, Patient patient) {
        return rehabProgramRepository.getAllProgramByDoctorAndPatient(doctor, patient);
    }


}
