package ru.rightcode.arm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.arm.dto.response.VariantResponse;
import ru.rightcode.arm.mapper.VariantResponseMapper;
import ru.rightcode.arm.model.Variant;
import ru.rightcode.arm.repository.VariantRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class VariantService {

    private final VariantRepository variantRepository;
    private final VariantResponseMapper variantResponseMapper;

    public List<VariantResponse> getModuleFormAnsweredVariants(Long moduleFormId) {
        List<Variant> answeredVariants = variantRepository.findAnsweredVariantsByModuleFormId(moduleFormId);

        return answeredVariants
                .stream()
                .map(variantResponseMapper::map)
                .toList();
    }

    public List<VariantResponse> getProgramFormAnsweredVariants(Long programFormId) {
        List<Variant> answeredVariants = variantRepository.findAnsweredVariantsByProgramFormId(programFormId);

        return answeredVariants
                .stream()
                .map(variantResponseMapper::map)
                .toList();
    }
}
