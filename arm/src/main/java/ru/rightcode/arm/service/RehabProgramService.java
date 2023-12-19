package ru.rightcode.arm.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rightcode.arm.dto.response.RehabProgramResponse;
import ru.rightcode.arm.model.Doctor;
import ru.rightcode.arm.model.Patient;
import ru.rightcode.arm.model.RehabProgram;
import ru.rightcode.arm.repository.RehabProgramRepository;
import ru.rightcode.arm.utils.ResponseMappers;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RehabProgramService {

    private final RehabProgramRepository rehabProgramRepository;

    private final DoctorService doctorService;

    private final PatientService patientService;

    /*
        TODO:
         Модуль анкет пока не реализован, создавать программу пока нельзя
     */
    public void create(String doctorLogin, Long patientId) {
        Doctor doctor = doctorService.getByLogin(doctorLogin);
        Patient patient = patientService.getById(patientId);

        RehabProgram rehabProgram = new RehabProgram();
        rehabProgram.setDoctor(doctor);
        rehabProgram.setPatient(patient);
        rehabProgram.setIsCurrent(true);
        rehabProgram.setStartDate(LocalDate.now());

        rehabProgramRepository.save(rehabProgram);
    }

    public RehabProgramResponse getCurrent(String doctorLogin, Long patientId) {
        Doctor doctor = doctorService.getByLogin(doctorLogin);
        Patient patient = patientService.getById(patientId);

        return ResponseMappers.mapToRehabProgramResponse(
                rehabProgramRepository.getCurrentProgramByDoctorAndPatient(doctor, patient)
                        .orElseThrow(() -> new EntityNotFoundException("У пациента нет активной программы реабилитации"))
        );
    }

    public List<RehabProgram> getAll(Doctor doctor, Patient patient) {
        return rehabProgramRepository.getAllProgramByDoctorAndPatient(doctor, patient);
    }
}
