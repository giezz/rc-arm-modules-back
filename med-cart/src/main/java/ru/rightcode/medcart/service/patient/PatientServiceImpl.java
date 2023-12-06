package ru.rightcode.medcart.service.patient;


import jakarta.jws.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rightcode.medcart.model.Patient;
import ru.rightcode.medcart.repository.PatientRepository;

import java.util.List;

@WebService
@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    @Autowired
    private final PatientRepository patientRepository;

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientByCode(Long code) {
        return patientRepository.findByPatientCode(code);
    }

}