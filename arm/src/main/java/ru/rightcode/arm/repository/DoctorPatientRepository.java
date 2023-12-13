package ru.rightcode.arm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.arm.model.DoctorPatientPk;
import ru.rightcode.arm.model.DoctorPatient;

@Repository
public interface DoctorPatientRepository extends JpaRepository<DoctorPatient, DoctorPatientPk> {
}
