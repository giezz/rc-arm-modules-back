package ru.rightcode.arm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rightcode.arm.dto.request.CreateProtocolRequest;
import ru.rightcode.arm.model.Protocol;

import java.time.Instant;

@RequiredArgsConstructor
@Service
public class ProtocolService {

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
