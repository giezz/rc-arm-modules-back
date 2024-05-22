package ru.rightcode.patient.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.patient.dto.response.HistoryResponse;
import ru.rightcode.patient.dto.response.RehabProgramResponse;
import ru.rightcode.patient.exception.BusinessException;
import ru.rightcode.patient.mapper.HistoryResponseMapper;
import ru.rightcode.patient.mapper.rehab.RehabProgramMapper;
import ru.rightcode.patient.model.Patient;
import ru.rightcode.patient.model.RehabProgram;
import ru.rightcode.patient.repository.RehabProgramRepository;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class RehabProgramService {

    private final RehabProgramRepository rehabProgramRepository;
    private final RehabProgramMapper rehabProgramMapper;

    private final HistoryResponseMapper historyResponseMapper;

    // Получение программы реабилитации по пациенту без доктора и пациента
    @Transactional
    protected RehabProgram getRehabProgramByPatient(Patient patient) {
        return rehabProgramRepository.findByPatientId(patient.getId())
                .orElseThrow(()  -> new BusinessException("Не найдено программы реабилитации"));
    }

    // Проверка актуальности программы реабилитации
    private RehabProgram checkRehabProgramIsCurrent(RehabProgram rehabProgram) {
        if (!rehabProgram.getIsCurrent()) {
            throw new BusinessException("Программа реабилитации неактуальна");
        }
        return rehabProgram;
    }

    private List<RehabProgram> checkAllRehabProgramIsNotCurrent(List<RehabProgram> rehabProgram) {
        // Проверка актуальности программы реабилитации, если программа неактуальна, то удаляем ее из списка
        rehabProgram.removeIf(RehabProgram::getIsNotCurrent);
        return rehabProgram;
    }

    // Получение программы реабилитации по пациенту
    @Cacheable(value = "RehabProgramService::getRehabProgramResponseByPatient", key = "#patient.id")
    public RehabProgramResponse getRehabProgramResponseByPatient(Patient patient) {
//        RehabProgram rehabProgram = getRehabProgramByPatient(patient);
        return rehabProgramMapper.toRehabProgramResponse(getRehabProgramByPatient(patient));
    }

    @Cacheable(value = "RehabProgramService::getHistoryResponseByPatient", key = "#rehabProgramSet.history")
    // Получение истории программы реабилитации по пациенту
    public Set<HistoryResponse> getHistoryResponseByPatient(List<RehabProgram> rehabProgramSet) {
        List<RehabProgram> rehabPrograms =
                checkAllRehabProgramIsNotCurrent(rehabProgramSet);
        return rehabPrograms.stream().map(historyResponseMapper::toResponse)
                .collect(java.util.stream.Collectors.toSet());
    }

}
