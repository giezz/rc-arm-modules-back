package ru.rightcode.medcart.service.patient;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rightcode.medcart.model.Patient;
import ru.rightcode.medcart.repository.PatientRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientByCode(Long code) {
        return patientRepository.findByPatientCode(code);
    }

//    @Override
//    public Patient getPatientByCode(Long id) {
//        return patientRepository.findByPatientCode(id);
//    }
//
//    @Override
//    public void addPatient(Patient patient) {
//        patientRepository.save(patient);
//    }
}
