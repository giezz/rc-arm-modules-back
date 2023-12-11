package ru.rightcode.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.core.model.DoctorPatient;
import ru.rightcode.core.model.DoctorPatientPk;

@Repository
public interface DoctorPatientRepository extends JpaRepository<DoctorPatient, DoctorPatientPk> {
}
