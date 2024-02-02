package ru.rightcode.arm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.repository.FormRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FormService {

    private final FormRepository formRepository;

}
