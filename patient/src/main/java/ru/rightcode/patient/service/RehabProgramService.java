package ru.rightcode.patient.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.patient.dto.response.history.HistoryResponse;
import ru.rightcode.patient.dto.response.moduleShort.ModuleResponse;
import ru.rightcode.patient.dto.response.rehab.RehabProgramResponse;
import ru.rightcode.patient.exception.BusinessException;
import ru.rightcode.patient.exception.NotFoundException;
import ru.rightcode.patient.mapper.HistoryResponseMapper;
import ru.rightcode.patient.mapper.module.ModuleResponseMapper;
import ru.rightcode.patient.mapper.rehab.RehabProgramMapper;
import ru.rightcode.patient.model.RehabProgram;

import java.util.Set;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class RehabProgramService {

//    private final RehabProgramRepository rehabProgramRepository;
    private final RehabProgramMapper rehabProgramMapper;

    private final HistoryResponseMapper historyResponseMapper;
    private final ModuleResponseMapper moduleResponseMapper;

    // Получение программы реабилитации по пациенту без доктора и пациента
//    @Transactional
//    protected RehabProgram getRehabProgramByPatient(Patient patient) {
//        return rehabProgramRepository.findByPatientId(patient.getId())
//                .orElseThrow(() -> new BusinessException("Не найдено программы реабилитации"));
//    }
//
//    // Проверка актуальности программы реабилитации
//    private RehabProgram checkRehabProgramIsCurrent(RehabProgram rehabProgram) {
//        if (!rehabProgram.getIsCurrent()) {
//            throw new BusinessException("Программа реабилитации неактуальна");
//        }
//        return rehabProgram;
//    }

    private Set<RehabProgram> checkAllRehabProgramIsNotCurrent(Set<RehabProgram> rehabProgram) {
        // Проверка актуальности программы реабилитации, если программа актуальна, то удаляем ее из списка
        rehabProgram.removeIf(RehabProgram::getIsCurrent);
        return rehabProgram;
    }

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

        // Проверка модуль относится к программе реабилитации пациента
        RehabProgram rehabProgram = rehabProgramsSet.stream()
                .filter(rp -> rp.getModules().stream()
                        .anyMatch(module -> module.getId().equals(moduleId)))
                .findFirst()
                .orElseThrow(() -> new BusinessException("Модуль не относится к программе реабилитации"));

        return moduleResponseMapper.toModuleResponse(rehabProgram.getModules().stream()
                .filter(module -> module.getId().equals(moduleId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Модуль не найден")));
    }

}
