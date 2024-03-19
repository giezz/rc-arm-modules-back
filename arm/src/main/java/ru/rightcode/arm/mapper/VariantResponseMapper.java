package ru.rightcode.arm.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rightcode.arm.dto.response.VariantResponse;
import ru.rightcode.arm.model.Variant;

@Component
@RequiredArgsConstructor
public class VariantResponseMapper implements Mapper<Variant, VariantResponse> {
    @Override
    public VariantResponse map(Variant object) {
        return new VariantResponse(
                object.getId(),
                object.getContent(),
                false
        );
    }
}
