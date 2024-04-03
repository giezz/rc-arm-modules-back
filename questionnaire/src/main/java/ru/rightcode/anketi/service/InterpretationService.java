package ru.rightcode.anketi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.anketi.exception.NotFoundException;
import ru.rightcode.anketi.model.Interpretation;
import ru.rightcode.anketi.repository.InterpretationRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class InterpretationService {
    private final InterpretationRepository interpretationRepository;

    public Interpretation getById(Long id) {
        return interpretationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Interpretation not found with id: " + id));
    }

    public List<Interpretation> getAll() {
        return interpretationRepository.findAll();
    }
}
