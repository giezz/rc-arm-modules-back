package ru.rightcode.medcart.service.hosphistory;

import ru.rightcode.medcart.dto.CreateRehabRecordRequest;
import ru.rightcode.medcart.dto.MedCartResponse;
import ru.rightcode.medcart.model.RehabilitationHistory;

import java.util.List;

public interface RehabilitationHistoryService {

    MedCartResponse<List<RehabilitationHistory>> getPatientHistory(Long patientCode);

    MedCartResponse<?> addRehabilitationRecord(CreateRehabRecordRequest request);
}
