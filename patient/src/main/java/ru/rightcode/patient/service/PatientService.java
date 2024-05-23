package ru.rightcode.patient.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.patient.dto.response.history.HistoryResponse;
import ru.rightcode.patient.dto.response.PatientResponse;
import ru.rightcode.patient.dto.response.moduleShort.ModuleResponse;
import ru.rightcode.patient.dto.response.rehab.RehabProgramResponse;
import ru.rightcode.patient.exception.NotFoundException;
import ru.rightcode.patient.mapper.PatientResponseMapper;
import ru.rightcode.patient.model.Patient;
import ru.rightcode.patient.repository.PatientRepository;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientResponseMapper patientResponseMapper;

    private final RehabProgramService rehabProgramService;

    @Transactional(readOnly = true)
    protected Patient getPatientByUsername(String username) {
        return patientRepository.getPatientByUserUsername(username)
                .orElseThrow(() -> new NotFoundException("Пациент не найден"));
    }

    @Transactional(readOnly = true)
    protected Patient getPatientRehabsProtocolsByUsername(String username) {
        return patientRepository.getPatientRehabProgramByUserUsername(username)
                .orElseThrow(() -> new NotFoundException("Пациент не найден или программа закончена"));
    }

    @Transactional(readOnly = true)
    protected Patient getPatientCurrentRehabsByUsername(String username) {
        return patientRepository.getPatientCurrentRehabProgramByUserUsername(username)
                .orElseThrow(() -> new NotFoundException("Пациент не найден"));
    }

    @Transactional
    protected Patient getPatientCurrentModuleByUsername(String username) {
        return patientRepository.getPatientCurrentModuleByUserUsername(username)
                .orElseThrow(() -> new NotFoundException("Пациент не найден"));
    }

    @Cacheable(value = "PatientService::getPatientByUsername", key = "#username")
    public PatientResponse getYourSelf(String username){
        return patientResponseMapper.toResponse(getPatientByUsername(username));
    }

    // История проведения реабилитаций
    // Результаты реабилитаций
    @Cacheable(value = "PatientService::getHistory", key = "#username")
    @Transactional
    public Set<HistoryResponse> getHistory(String username){
        Patient patientFromDB = getPatientRehabsProtocolsByUsername(username);
        return rehabProgramService.getHistoryResponseByPatient(patientFromDB.getRehabPrograms());
    }

    // ПРограмма реабилитации
    @Cacheable(value = "PatientService::getRehabProgram", key = "#username")
    @Transactional
    public RehabProgramResponse getRehabProgram(String username){
        Patient patientFromDB = getPatientCurrentRehabsByUsername(username);
        return rehabProgramService.getRehabProgramResponseByPatient(patientFromDB.getRehabPrograms());
    }

    // Модули реабилитации
    @Cacheable(value = "PatientService::getModule", key = "#moduleId")
    @Transactional
    public ModuleResponse getModule(String username, Long moduleId){
        Patient patientFromDB = getPatientCurrentModuleByUsername(username);
        return rehabProgramService.getModuleByPatientModuleId(patientFromDB.getRehabPrograms(), moduleId);
    }
    // TODO: get Эпикриз
}
