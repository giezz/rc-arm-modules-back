package ru.rightcode.patient.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.patient.dto.response.form.FormResponse;
import ru.rightcode.patient.dto.response.history.HistoryResponse;
import ru.rightcode.patient.dto.response.PatientResponse;
import ru.rightcode.patient.dto.response.moduleShort.ExerciseShortResponse;
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

    // Транзакции
    // Получение пациента с паспортом и статусом
    @Transactional(readOnly = true)
    protected Patient getPatientByUsername(String username) {
        return patientRepository.getPatientByUserUsername(username)
                .orElseThrow(() -> new NotFoundException("Пациент не найден"));
    }

    // Получение пациента с протоколом и программой реабилитации
    @Transactional(readOnly = true)
    protected Patient getPatientRehabsProtocolsByUsername(String username) {
        return patientRepository.getPatientRehabProgramByUserUsername(username)
                .orElseThrow(() -> new NotFoundException("Пациент не найден или программа закончена"));
    }

    // Получение пациента с текущей программой реабилитации
    @Transactional(readOnly = true)
    protected Patient getPatientCurrentRehabsByUsername(String username) {
        return patientRepository.getPatientCurrentRehabProgramByUserUsername(username)
                .orElseThrow(() -> new NotFoundException("Пациент не найден"));
    }

    // Получение пациента с данными о модуле и текущей программой реабилитации
    @Transactional
    protected Patient getPatientCurrentModuleByUsername(String username) {
        return patientRepository.getPatientCurrentModuleByUserUsername(username)
                .orElseThrow(() -> new NotFoundException("Пациент не найден"));
    }

//    @Transactional
//    protected Patient getPatientFormCurrentModuleByUsername(String username, Long moduleId, Long formId) {
//        return patientRepository.getPatientCurrentModuleFormQuestionByUserUsername(username, moduleId, formId)
//                .orElseThrow(() -> new NotFoundException("Пациент не найден"));
//    }

    // Методы для работы с пациентом
    // Пациент
    @Cacheable(value = "PatientService::getPatientByUsername", key = "#username")
    public PatientResponse getYourSelf(String username) {
        return patientResponseMapper.toResponse(getPatientByUsername(username));
    }

    // История проведения реабилитаций
    // Результаты реабилитаций
    @Cacheable(value = "PatientService::getHistory", key = "#username")
    @Transactional
    public Set<HistoryResponse> getHistory(String username) {
        Patient patientFromDB = getPatientRehabsProtocolsByUsername(username);
        return rehabProgramService.getHistoryResponseByPatient(patientFromDB.getRehabPrograms());
    }

    // ПРограмма реабилитации
    @Cacheable(value = "PatientService::getRehabProgram", key = "#username")
    @Transactional
    public RehabProgramResponse getRehabProgram(String username) {
        Patient patientFromDB = getPatientCurrentRehabsByUsername(username);
        return rehabProgramService.getRehabProgramResponseByPatient(patientFromDB.getRehabPrograms());
    }

    // Модуль реабилитации по Id
    @Cacheable(value = "PatientService::getModule", key = "#moduleId")
    @Transactional
    public ModuleResponse getModule(String username, Long moduleId) {
        Patient patientFromDB = getPatientCurrentModuleByUsername(username);
        return rehabProgramService.getModuleByPatientModuleId(patientFromDB.getRehabPrograms(), moduleId);
    }

    // Упражнение модуля реабилитации по Id
    @Cacheable(value = "PatientService::getExerciseByModuleIdExerciseId", key = "#exerciseId")
    @Transactional
    public ExerciseShortResponse getExerciseByModuleIdExerciseId(String username, Long moduleId, Long exerciseId) {
        Patient patientFromDB = getPatientCurrentModuleByUsername(username);
        return rehabProgramService.getExerciseByPatientModuleId(patientFromDB.getRehabPrograms(), moduleId, exerciseId);
    }

    // Анкета модуля реабилитации по Id
    // TODO: multiply selects FormQuestions
    // необходимо сделать запрос к базе
    @Cacheable(value = "PatientService::getFormByModuleIdFormId", key = "#formId")
    @Transactional
    public FormResponse getFormByModuleIdFormId(String username, Long moduleId, Long formId) {
        Patient patientFromDB = getPatientCurrentModuleByUsername(username);
        return rehabProgramService.getFormByPatientModuleId(patientFromDB.getRehabPrograms(), moduleId, formId);
    }

    // Анкета модуля реабилитации по Id
    // TODO: multiply selects FormQuestions
    // необходимо сделать запрос к базе
    @Cacheable(value = "PatientService::getFormResponseByProgramIdFormId", key = "#programFormId")
    @Transactional
    public FormResponse getFormResponseByProgramIdFormId(
            String username, Long programFormId) {
        Patient patientFromDB = getPatientCurrentRehabsByUsername(username);
        return rehabProgramService.getFormResponseByProgramId(
                patientFromDB.getRehabPrograms(), programFormId);
    }
}
