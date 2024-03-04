package ru.rightcode.arm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.dto.response.FormResultResponse;
import ru.rightcode.arm.mapper.FormResultResponseMapper;
import ru.rightcode.arm.repository.FormResultRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class FormResultService {

    private final FormResultRepository formResultRepository;
    private final FormResultResponseMapper formResultResponseMapper;

    public List<FormResultResponse> getResults(Long programId) {
//        return formResultRepository.findByRehabProgramId(programId)
//                .stream()
//                .map(formResultResponseMapper::map)
//                .toList();
        return null;
    }
}
