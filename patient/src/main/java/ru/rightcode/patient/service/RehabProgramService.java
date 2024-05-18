package ru.rightcode.patient.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.patient.repository.RehabProgramRepository;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class RehabProgramService {

    private final RehabProgramRepository rehabProgramRepository;

}
