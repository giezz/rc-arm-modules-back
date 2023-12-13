package ru.rightcode.arm.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(schema = "doc", name = "doctor_patient")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DoctorPatient {

    @EmbeddedId
    private DoctorPatientPk id;

    public DoctorPatient(Long doctorId, Long patientId) {
        this.id = new DoctorPatientPk(doctorId, patientId);
    }
}
