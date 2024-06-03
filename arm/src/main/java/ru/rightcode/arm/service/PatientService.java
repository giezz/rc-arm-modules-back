package ru.rightcode.arm.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.dto.projection.RehabProgramInfo;
import ru.rightcode.arm.dto.request.PatientRequest;
import ru.rightcode.arm.dto.response.PageableResponse;
import ru.rightcode.arm.dto.response.PatientResponse;
import ru.rightcode.arm.dto.response.RehabProgramResponse;
import ru.rightcode.arm.exception.PatientNotFoundException;
import ru.rightcode.arm.mapper.PatientMapper;
import ru.rightcode.arm.mapper.RehabProgramMapper;
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

    private final RehabProgramMapper rehabProgramMapper;
    private final PatientMapper patientMapper;

    public PageableResponse<PatientResponse> getAll(int pageNumber, int pageSize, PatientRequest patientRequest) {
        Optional<Specification<Patient>> spec = PatientSpecification.specificationBuilder(patientRequest);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        Page<Patient> page = spec
                .map(specification -> patientRepository.findAll(specification, pageable))
                .orElseGet(() -> patientRepository.findAll(pageable));

        return new PageableResponse<>(
                page.get().map(patientMapper::toDto).toList(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements()
        );
    }

    public PatientResponse getByCode(Long code) {
        return patientMapper.toDto(getPatientByCode(code));
    }

    public RehabProgramResponse getCurrentRehabProgram(Long code) {
        Patient patient = getPatientByCode(code);
        RehabProgram rehabProgram = rehabProgramRepository.findCurrentWithModules(patient.getId())
                .orElseThrow(EntityNotFoundException::new);
        rehabProgramRepository
                .findCurrentWithProgramForms(patient.getId())
                .orElseThrow(() -> new EntityNotFoundException("Нет 2"));

        return rehabProgramMapper.toDto(rehabProgram);
    }

    public RehabProgramResponse getRehabProgram(Long patientCode, Long programId) {
        Patient patient = getPatientByCode(patientCode);
        return rehabProgramMapper.toDto(getRehabProgramByPatientId(programId, patient.getId()));
    }

    public List<RehabProgramInfo> getAllRehabPrograms(Long code) {
        return rehabProgramRepository.findAllByPatientId(getPatientByCode(code).getId());
    }

    private Patient getPatientByCode(Long code) {
        return patientRepository.findByPatientCode(code)
                .orElseThrow(() -> new PatientNotFoundException(code));
    }

    private RehabProgram getRehabProgramByPatientId(Long programId, Long patientId) {
        RehabProgram rehabProgram = rehabProgramRepository
                .findByPatientIdWithModules(programId, patientId)
                .orElseThrow(() -> new EntityNotFoundException("У пациента нет данной программы реабилитации"));
        rehabProgramRepository
                .findByPatientIdWithProgramForms(programId, patientId)
                .orElseThrow(() -> new EntityNotFoundException("У пациента нет данной программы реабилитации"));

        return rehabProgram;
    }

}
