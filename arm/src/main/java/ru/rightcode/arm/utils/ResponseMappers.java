package ru.rightcode.arm.utils;

import ru.rightcode.arm.dto.response.SimpleDoctorResponse;
import ru.rightcode.arm.dto.response.SimplePatientResponse;
import ru.rightcode.arm.model.Doctor;
import ru.rightcode.arm.model.Patient;

public final class ResponseMappers {

    public static SimpleDoctorResponse mapToSimpleDoctorResponse(Doctor doctor) {
        return SimpleDoctorResponse.builder()
                .id(doctor.getId())
                .doctorCode(doctor.getDoctorCode())
                .firstName(doctor.getFirstName())
                .middleName(doctor.getMiddleName())
                .lastName(doctor.getLastName())
                .email(doctor.getEmail())
                .build();
    }

    public static SimplePatientResponse mapToSimplePatientResponse(Patient patient) {
        SimplePatientResponse.SimplePatientResponseBuilder builder = SimplePatientResponse.builder()
                .id(patient.getId())
                .patientCode(patient.getPatientCode())
                .firstName(patient.getFirstName())
                .middleName(patient.getMiddleName())
                .lastName(patient.getLastName())
                .birthDate(patient.getBirthDate())
                .deathDate(patient.getDeathDate())
                .address(patient.getAddress())
                .phoneNumber(patient.getPhoneNumber())
                .workPlaceData(patient.getWorkPlaceData())
                .bookmark(patient.getBookmark())
                .snils(patient.getSnils())
                .polis(patient.getPolis())
                .patientStatus(patient.getPatientStatus())
                .passport(patient.getPassport());

        if (patient.getDoctor() != null) {
            return builder.doctor(mapToSimpleDoctorResponse(patient.getDoctor())).build();
        }

        return builder.build();
    }
}
