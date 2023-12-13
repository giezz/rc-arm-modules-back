package ru.rightcode.arm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class DoctorPatientPk implements Serializable {

    @Column(name = "doctor_id")
    Long doctorId;

    @Column(name = "patient_id")
    Long patientId;

    public DoctorPatientPk(Long doctorId, Long patientId) {
        this.doctorId = doctorId;
        this.patientId = patientId;
    }
}
