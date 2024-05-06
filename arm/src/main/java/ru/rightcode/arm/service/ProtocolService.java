package ru.rightcode.arm.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rightcode.arm.dto.request.CreateProtocolRequest;
import ru.rightcode.arm.dto.response.ProtocolResponse;
import ru.rightcode.arm.mapper.ProtocolResponseMapper;
import ru.rightcode.arm.model.Protocol;
import ru.rightcode.arm.repository.ProtocolRepository;

import java.time.Instant;

@RequiredArgsConstructor
@Service
public class ProtocolService {

    private final ProtocolRepository protocolRepository;
    private final ProtocolResponseMapper protocolResponseMapper;

    public Protocol createProtocol(CreateProtocolRequest request) {
        String results = transformFormsResults(request.modulesFormsResults(), request.programFormsResults());
        Protocol protocol = new Protocol();
        protocol.setCreationDate(Instant.now());
        protocol.setIsFinal(false);
        protocol.setScalesResult(results);
        protocol.setRecommendations(request.recommendations());
        protocol.setRehabResult(request.result());
        protocol.setRehabDiagnosis(request.diagnosis());

        return protocol;
    }

    public ProtocolResponse getProtocol(Long id) {
        return protocolResponseMapper.map(protocolRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new));
    }

    private String transformFormsResults(String modulesFormsResults, String programFormsResults) {
        StringBuilder result = new StringBuilder("Результаты входных/выходных анкет:\n");
        if (!programFormsResults.isEmpty()) {
            result.append(programFormsResults).append("\n");
        } else {
            result.append("Результаты отсутствуют\n");
        }
        result.append("Результаты промежуточных анкет:\n");
        if (!modulesFormsResults.isEmpty()) {
            result.append(modulesFormsResults).append("\n");
        } else {
            result.append("Результаты отсутствуют\n");
        }

        return result.toString();
    }

}
