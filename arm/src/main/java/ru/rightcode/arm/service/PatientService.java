package ru.rightcode.arm.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.dto.projection.RehabProgramInfo;
import ru.rightcode.arm.dto.request.PatientRequest;
import ru.rightcode.arm.dto.response.PatientResponse;
import ru.rightcode.arm.dto.response.RehabProgramResponse;
import ru.rightcode.arm.exceptions.PatientNotFoundException;
import ru.rightcode.arm.mapper.PatientResponseMapper;
import ru.rightcode.arm.mapper.RehabProgramResponseMapper;
import ru.rightcode.arm.model.Patient;
import ru.rightcode.arm.model.RehabProgram;
import ru.rightcode.arm.repository.PatientRepository;
import ru.rightcode.arm.repository.RehabProgramRepository;
import ru.rightcode.arm.repository.specification.PatientSpecification;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PatientService {

    private final PatientRepository patientRepository;
    private final RehabProgramRepository rehabProgramRepository;

    private final PatientResponseMapper patientResponseMapper;
    private final RehabProgramResponseMapper rehabProgramResponseMapper;

    public List<PatientResponse> getAll(PatientRequest patientRequest) {
        Optional<Specification<Patient>> spec = PatientSpecification.specificationBuilder(patientRequest);

        return spec.map(patientRepository::findAll)
                .orElseGet(patientRepository::findAll)
                .stream()
                .map(patientResponseMapper::mapDetails)
                .toList();
    }

    public PatientResponse getByCode(Long code) {
        final Patient patient = patientRepository.findByPatientCode(code)
                .orElseThrow(() -> new PatientNotFoundException(code));

        return patientResponseMapper.mapDetails(patient);
    }

    public RehabProgramResponse getCurrentRehabProgram(Long code) {
        Patient patient = patientRepository
                .findByPatientCode(code)
                .orElseThrow(() -> new PatientNotFoundException(code));
        RehabProgram rehabProgram = rehabProgramRepository.findCurrentWithModules(patient.getId())
                .orElseThrow(EntityNotFoundException::new);
        rehabProgramRepository
                .findCurrentWithProgramForms(patient.getId())
                .orElseThrow(() -> new EntityNotFoundException("Нет 2"));

        return rehabProgramResponseMapper.mapDetails(rehabProgram);
    }

    public List<RehabProgramInfo> getAllRehabPrograms(Long code) {
        Patient patient = patientRepository
                .findByPatientCode(code)
                .orElseThrow(() -> new PatientNotFoundException(code));

        return rehabProgramRepository.findAllByPatientId(patient.getId());
    }

}
