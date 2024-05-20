package ru.rightcode.arm.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rightcode.arm.dto.response.ProtocolResponse;
import ru.rightcode.arm.model.Protocol;

@Component
@RequiredArgsConstructor
public class ProtocolResponseMapper implements Mapper<Protocol, ProtocolResponse> {

    @Override
    public ProtocolResponse map(Protocol object) {
        return new ProtocolResponse(
                object.getId(),
                object.getCreationDate(),
                object.getIsFinal(),
                object.getScalesResult(),
                object.getRehabResult(),
                object.getRecommendations(),
                object.getRehabDiagnosis()
        );
    }
}
