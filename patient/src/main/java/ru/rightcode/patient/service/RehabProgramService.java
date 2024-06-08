package ru.rightcode.patient.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.patient.dto.response.form.FormResponse;
import ru.rightcode.patient.dto.response.history.HistoryResponse;
import ru.rightcode.patient.dto.response.moduleShort.ExerciseShortResponse;
import ru.rightcode.patient.dto.response.moduleShort.FormShortResponse;
import ru.rightcode.patient.dto.response.moduleShort.ModuleResponse;
import ru.rightcode.patient.dto.response.rehab.FormNoQuestionsResponse;
import ru.rightcode.patient.dto.response.rehab.RehabProgramResponse;
import ru.rightcode.patient.exception.BusinessException;
import ru.rightcode.patient.exception.NotFoundException;
import ru.rightcode.patient.mapper.FormProgramResponseMapper;
import ru.rightcode.patient.mapper.HistoryResponseMapper;
import ru.rightcode.patient.mapper.module.ExerciseShortResponseMapper;
import ru.rightcode.patient.mapper.module.FormShortResponseMapper;
import ru.rightcode.patient.mapper.module.ModuleResponseMapper;
import ru.rightcode.patient.mapper.rehab.RehabProgramMapper;
import ru.rightcode.patient.model.*;
import ru.rightcode.patient.model.Module;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class RehabProgramService {

    //    private final RehabProgramRepository rehabProgramRepository;
//    private final ModuleService moduleService;
    private final CheckAnswersFormService checkAnswersFormService;

    private final RehabProgramMapper rehabProgramMapper;
    private final HistoryResponseMapper historyResponseMapper;
    private final ModuleResponseMapper moduleResponseMapper;
    private final ExerciseShortResponseMapper exerciseShortResponseMapper;
    private final FormProgramResponseMapper formProgramResponseMapper;
    private final FormShortResponseMapper formShortResponseMapper;

    // Получение программы реабилитации по пациенту
    public RehabProgramResponse getRehabProgramResponseByPatient(Set<RehabProgram> rehabPrograms) {
        // Проверка актуальности программы реабилитации, если программа неактуальна, то удаляем ее из списка
        rehabPrograms.removeIf(RehabProgram::getIsNotCurrent);

        RehabProgram rh = rehabPrograms.stream().findFirst()
                .orElseThrow(() -> new BusinessException("Не найдено программы реабилитации"));
        return rehabProgramMapper.toRehabProgramResponse(rh);
    }

    // Получение истории программы реабилитации по пациенту
    public Set<HistoryResponse> getHistoryResponseByPatient(Set<RehabProgram> historyRehabPrograms) {
        Set<RehabProgram> rehabPrograms =
                checkAllRehabProgramIsNotCurrent(historyRehabPrograms);
        return rehabPrograms.stream().map(historyResponseMapper::toResponse)
                .collect(java.util.stream.Collectors.toSet());
    }

    public ModuleResponse getModuleByPatientModuleId(Set<RehabProgram> rehabProgramsSet, Long moduleId) {
        // Проверка актуальности программы реабилитации, если программа неактуальна, то удаляем ее из списка
        rehabProgramsSet.removeIf(RehabProgram::getIsNotCurrent);
        return moduleResponseMapper.toModuleResponse(getActualModuleByModuleId(rehabProgramsSet, moduleId));
    }

    public ExerciseShortResponse getExerciseByPatientModuleId(Set<RehabProgram> rehabProgramsSet, Long moduleId, Long exerciseId) {
        // Проверка актуальности программы реабилитации, если программа неактуальна, то удаляем ее из списка
        rehabProgramsSet.removeIf(RehabProgram::getIsNotCurrent);
        Module module = getActualModuleByModuleId(rehabProgramsSet, moduleId);
        ModuleExercise exercise = module.getModuleExercises()
                .stream()
                .filter(ex -> ex.getId().equals(exerciseId)
                ).findFirst()
                .orElseThrow(() -> new NotFoundException("Упражнение не найдено"));

        return exerciseShortResponseMapper.toExerciseShortResponse(exercise);
    }

    // Получение анкеты из модуля программы реабилитации по пациенту и id модуля и id анкеты
    public FormResponse getFormByPatientModuleId(Set<RehabProgram> rehabProgramsSet, Long moduleId, Long formId) {
        // Проверка актуальности программы реабилитации, если программа неактуальна, то удаляем ее из списка
        rehabProgramsSet.removeIf(RehabProgram::getIsNotCurrent);
        Module module = getActualModuleByModuleId(rehabProgramsSet, moduleId);
        ModuleForm moduleForm = module.getModuleForms()
                .stream()
                .filter(ex -> ex.getId().equals(formId))
                .findFirst().filter(pf -> pf.getFinishedAt() != null)
                .orElseThrow(() -> new NotFoundException("Анкета не найдена"));
        Form form = moduleForm.getForm();
        if (checkAnswersFormService.checkModule(moduleForm.getId())) {
            return formProgramResponseMapper.toResponse(form, true);
        }
        return formProgramResponseMapper.toResponse(form, false);
    }

    // Получение анкеты из программы реабилитации по пациенту и id программАнкеты
    public FormResponse getFormResponseByProgramId(Set<RehabProgram> rehabProgramsSet, Long programFormId) {
        // Проверка актуальности программы реабилитации, если программа неактуальна, то удаляем ее из списка
        rehabProgramsSet.removeIf(RehabProgram::getIsNotCurrent);
        RehabProgram rehabProgram = rehabProgramsSet.stream().
                filter(rp -> rp.getForms().stream()
                        .anyMatch(pf -> pf.getId().equals(programFormId)))
                .findFirst()
                .orElseThrow(() -> new BusinessException("Анкеты не актуальны для программы реабилитации"));
        ProgramForm programForm = rehabProgram.getForms().stream()
                .filter(pf -> pf.getId().equals(programFormId))
                .findFirst()
                .filter(pr -> pr.getFinishedAt() != null)
                .orElseThrow(() -> new NotFoundException("Анкета не найдена"));
        Form formFromProgramForm = programForm.getForm();
        boolean isAnswered = checkAnswersFormService.checkProgram(programForm.getId(), formFromProgramForm);
        if (isAnswered) {
            return formProgramResponseMapper.toResponse(formFromProgramForm, true);
        }
        return formProgramResponseMapper.toResponse(formFromProgramForm, false);
    }

    public List<FormShortResponse> getFormNoQuestionsResponsesByModuleId(Set<RehabProgram> rehabProgramsSet, Long moduleId)  {
        // dublicate code
        // Проверка актуальности программы реабилитации, если программа неактуальна, то удаляем ее из списка
        rehabProgramsSet.removeIf(RehabProgram::getIsNotCurrent);
        Module module = getActualModuleByModuleId(rehabProgramsSet, moduleId);
        List<FormShortResponse> moduleForm = module.getModuleForms()
                .stream()
                .filter((ModuleForm form) -> checkAnswersFormService.checkModule(form.getId()))
                .map(formShortResponseMapper::toFormShortResponse)
                .toList();
        return moduleForm;
    }

    // получение программы реабилитации по id модуля
    private RehabProgram getActualRehabProgramByRehabProgramId(Set<RehabProgram> rehabPrograms, Long moduleId) {
        return rehabPrograms.stream()
                .filter(rp -> rp.getModules().stream()
                        .anyMatch(module -> module.getId().equals(moduleId)))
                .findFirst()
                .orElseThrow(() -> new BusinessException("Модуль не относится к программе реабилитации"));
    }

    // Получение модуля из программы реабилитации по пациенту
    private Module getActualModuleByModuleId(Set<RehabProgram> rehabPrograms, Long moduleId) {
        // Проверка модуль относится к программе реабилитации пациента
        RehabProgram rehabProgram = getActualRehabProgramByRehabProgramId(rehabPrograms, moduleId);
        return rehabProgram.getModules().stream()
                .filter(module -> module.getId().equals(moduleId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Модуль не найден"));
    }

    // Проверка актуальности программы реабилитации, если программа актуальна, то удаляем ее из списка
    private Set<RehabProgram> checkAllRehabProgramIsNotCurrent(Set<RehabProgram> rehabProgram) {
        // Проверка актуальности программы реабилитации, если программа актуальна, то удаляем ее из списка
        rehabProgram.removeIf(RehabProgram::getIsCurrent);
        return rehabProgram;
    }
}
