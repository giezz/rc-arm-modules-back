package ru.rightcode.arm.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.rightcode.arm.dto.response.ExerciseResponse;
import ru.rightcode.arm.model.Exercise;

@Component
@RequiredArgsConstructor
public class ExerciseResponseMapper implements Mapper<Exercise, ExerciseResponse> {
    @Override
    public ExerciseResponse map(Exercise object) {
        String type = null;
        if (object.getExerciseType() != null) {
            type = object.getExerciseType().getName();
        }

        return new ExerciseResponse(
                object.getId(),
                object.getName(),
                object.getVideoUrl(),
                object.getDescription(),
                type
        );
    }
}
