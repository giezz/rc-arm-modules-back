package ru.rightcode.arm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.dto.request.CreateMedCardRehabRecordRequest;
import ru.rightcode.arm.dto.request.CreateProtocolRequest;
import ru.rightcode.arm.dto.response.ProtocolResponse;
import ru.rightcode.arm.mapper.ProtocolResponseMapper;
import ru.rightcode.arm.model.Doctor;
import ru.rightcode.arm.model.Patient;
import ru.rightcode.arm.model.Protocol;
import ru.rightcode.arm.repository.ProtocolRepository;
import ru.rightcode.arm.service.medcard.MedCardRehabilitationHistoryService;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProtocolService {

    private final ProtocolRepository protocolRepository;
    private final ProtocolResponseMapper protocolResponseMapper;

    private final MedCardRehabilitationHistoryService medCardRehabilitationHistoryService;

    public Protocol createProtocol(CreateProtocolRequest request) {
        String results = transformFormsResults(request.modulesFormsResults(), request.programFormsResults());
        Protocol protocol = new Protocol();
        protocol.setCreationDate(Instant.now());
        protocol.setIsFinal(request.isFinal());
        protocol.setScalesResult(results);
        protocol.setRecommendations(request.recommendations());
        protocol.setRehabResult(request.result());
        protocol.setRehabDiagnosis(request.diagnosis());

        return protocol;
    }

    public void createMedCardRehabHistoryRecord(Patient patient, Doctor doctor, CreateProtocolRequest request) {
        String results = transformFormsResults(request.modulesFormsResults(), request.programFormsResults());
        String result = results + request.diagnosis() + "\n" +
                request.result() + "\n" +
                request.recommendations();
        CreateMedCardRehabRecordRequest rehabRecordRequest = new CreateMedCardRehabRecordRequest(
                patient.getPatientCode(),
                12345L,
                doctor.getDoctorCode(),
                LocalDate.now(),
                result
        );
        medCardRehabilitationHistoryService.createRehabHistoryRecord(rehabRecordRequest);
    }

    @Transactional(readOnly = true)
    public List<ProtocolResponse> getProtocolsByProgramId(Long id) {
        return protocolRepository.findByRehabProgramId(id).
                stream()
                .map(protocolResponseMapper::map)
                .toList();
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
