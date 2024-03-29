package ru.rightcode.arm.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.dto.request.CreateProtocolRequest;
import ru.rightcode.arm.model.Protocol;
import ru.rightcode.arm.model.RehabProgram;
import ru.rightcode.arm.repository.ProtocolRepository;
import ru.rightcode.arm.repository.RehabProgramRepository;

import java.time.Instant;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ProtocolService {

    private final RehabProgramService rehabProgramService;

    private final ProtocolRepository protocolRepository;
    private final RehabProgramRepository rehabProgramRepository;

    @Transactional
    public void create(Long rehabProgramId, CreateProtocolRequest request) {
        RehabProgram rehabProgram = rehabProgramRepository.findById(rehabProgramId)
                .orElseThrow(EntityNotFoundException::new);
        rehabProgram.setIsCurrent(false);
        rehabProgram.setEndDate(Instant.now());
        Protocol protocol = new Protocol();
        protocol.setRehabProgram(rehabProgram);
        protocol.setCreationDate(Instant.now());
        protocol.setIsFinal(true);
        protocol.setScalesResult(request.scalesResult());
        protocol.setRecommendations(request.recommendations());
        protocol.setRehabResult(request.result());
        protocol.setRehabDiagnosis(request.diagnosis());

        rehabProgramRepository.save(rehabProgram);
        protocolRepository.save(protocol);
    }
}
