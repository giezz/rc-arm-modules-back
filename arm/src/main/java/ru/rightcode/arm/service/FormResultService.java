package ru.rightcode.arm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.dto.response.FormResultResponse;
import ru.rightcode.arm.mapper.FormResultResponseMapper;
import ru.rightcode.arm.model.FormResult;
import ru.rightcode.arm.repository.FormResultRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FormResultService {

    private final FormResultRepository formResultRepository;
    private final FormResultResponseMapper formResultResponseMapper;

    public List<FormResultResponse> getResults(Long patientId, Long formId) {
        return formResultRepository.findByPatientIdAndFormId(patientId, formId)
                .stream()
                .map(formResultResponseMapper::map)
                .toList();
    }

    public List<FormResultResponse> getResults(Long patientId) {
        return formResultRepository.findByPatientId(patientId)
                .stream()
                .map(formResultResponseMapper::map)
                .toList();
    }

    public List<FormResultResponse> getResults(Long patientId, List<Long> formIds) {
        return formResultRepository.findByPatientIdAndFormIdIn(patientId, formIds)
                .stream()
                .map(formResultResponseMapper::map)
                .toList();
    }
}
