package ru.rightcode.patient.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.patient.dto.response.HistoryResponse;
import ru.rightcode.patient.dto.response.PatientResponse;
import ru.rightcode.patient.dto.response.module.ModuleResponse;
import ru.rightcode.patient.dto.response.RehabProgramResponse;
import ru.rightcode.patient.exception.NotFoundException;
import ru.rightcode.patient.mapper.PatientResponseMapper;
import ru.rightcode.patient.model.Patient;
import ru.rightcode.patient.repository.PatientRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientResponseMapper patientResponseMapper;

    private final RehabProgramService rehabProgramService;
    private final ModuleService moduleService;

    @Transactional
    protected Patient getPatientByUsername(String username) {
        return patientRepository.getPatientByUserUsername(username)
                .orElseThrow(() -> new NotFoundException("Пациент не найден"));
    }

    @Transactional
    protected Patient getPatientRehabsProtocolsByUsername(String username) {
        return patientRepository.getPatientRehabProgramByUserUsername(username)
                .orElseThrow(() -> new NotFoundException("Пациент не найден или программа закончена"));
    }

    @Transactional
    protected Patient getPatientCurrentRehabsByUsername(String username) {
        return patientRepository.getPatientCurrentRehabProgramByUserUsername(username)
                .orElseThrow(() -> new NotFoundException("Пациент не найден"));
    }

//    @Cacheable(value = "PatientService::getYourSelf", key = "#username")
    public PatientResponse getYourSelf(String username){
        return patientResponseMapper.toResponse(getPatientByUsername(username));
    }

    // История проведения реабилитаций
    // Результаты реабилитаций
    @Transactional
    public Set<HistoryResponse> getHistory(String username){
        Patient patientFromDB = getPatientRehabsProtocolsByUsername(username);
        return rehabProgramService.getHistoryResponseByPatient(patientFromDB.getRehabPrograms());
    }

    // ПРограмма реабилитации
    @Transactional
    public RehabProgramResponse getRehabProgram(String username){
        Patient patientFromDB = getPatientByUsername(username);
        return rehabProgramService.getRehabProgramResponseByPatient(patientFromDB);
    }

    // Модули реабилитации
    @Transactional
    public ModuleResponse getModule(String username, Long moduleId){
        Patient patientFromDB = getPatientByUsername(username);
        return moduleService.getModuleResponse(moduleId);
    }
    // TODO: get Эпикриз
}
