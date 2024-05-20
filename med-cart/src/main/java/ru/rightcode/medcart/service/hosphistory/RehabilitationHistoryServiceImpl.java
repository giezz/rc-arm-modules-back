package ru.rightcode.medcart.service.hosphistory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.medcart.dto.CreateRehabRecordRequest;
import ru.rightcode.medcart.dto.MedCartResponse;
import ru.rightcode.medcart.model.Patient;
import ru.rightcode.medcart.model.RehabilitationHistory;
import ru.rightcode.medcart.repository.PatientRepository;
import ru.rightcode.medcart.repository.RehabilitationHistoryRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class RehabilitationHistoryServiceImpl implements RehabilitationHistoryService {

    private final RehabilitationHistoryRepository rehabilitationHistoryRepository;
    private final PatientRepository patientRepository;

    @Override
    public MedCartResponse<List<RehabilitationHistory>> getPatientHistory(Long patientCode) {
        if (!patientRepository.existsByPatientCode(patientCode)) {
            return new MedCartResponse<>(
                    "error",
                    null
            );
        }

        return new MedCartResponse<>(
                "success",
                rehabilitationHistoryRepository.findAllByPatientPatientCode(patientCode)
        );
    }

    @Transactional
    @Override
    public MedCartResponse<String> addRehabilitationRecord(CreateRehabRecordRequest request) {
        Optional<Patient> byPatientCode = patientRepository.findByPatientCode(request.getPatientCode());
        if (byPatientCode.isEmpty()) {
            return new MedCartResponse<>(
                    "error",
                    null
            );
        }
        rehabilitationHistoryRepository.save(new RehabilitationHistory(
                byPatientCode.get(),
                request.getMuCode(),
                request.getDoctorCode(),
                request.getCreationDate(),
                request.getRehabResult()
        ));

        return new MedCartResponse<>(
                "success",
                null
        );
    }
}
