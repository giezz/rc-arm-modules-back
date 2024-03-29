package ru.rightcode.anketi.service.variant;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.anketi.dto.VariantDto;
import ru.rightcode.anketi.exception.NotFoundException;
import ru.rightcode.anketi.mapper.mapstruct.VariantMapper;
import ru.rightcode.anketi.model.Question;
import ru.rightcode.anketi.model.Variant;
import ru.rightcode.anketi.repository.VariantRepository;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class VariantServiceImpl  {

    @Autowired
    private final VariantRepository variantRepository;

    private final VariantMapper variantMapper;

    @Transactional
    public Variant getVariantById(Long variantId) {
        return variantRepository.findById(variantId)
                .orElseThrow(() -> new NotFoundException("Variant not found with id: " + variantId));
    }

    public void deleteByQuestionId(Long questionId) {
        variantRepository.deleteByQuestion_id(questionId);
    }

    public void processVariants(Set<VariantDto> variantDTOs, Question question) {
        // Проходимся по всем вариантам в DTO и обновляем или создаем соответствующие варианты
        for (VariantDto variantDTO : variantDTOs) {
            Variant variant;
            // Проверяем, указан ли ID варианта
            if (variantDTO.getId() != null) {
                // Если ID указан, получаем существующий вариант из базы данных
                variant = getVariantById(variantDTO.getId());
                // Обновляем поля существующего варианта
                variant.setContent(variantDTO.getContent());
                variant.setScore(variantDTO.getScore());
            } else {
                // Если ID не указан, создаем новый вариант
                variant = variantMapper.toEntity(variantDTO);
                variant.setQuestion_id(question);
            }
            // Сохраняем или обновляем вариант в базе данных
            variantRepository.save(variant);
        }
    }
}
