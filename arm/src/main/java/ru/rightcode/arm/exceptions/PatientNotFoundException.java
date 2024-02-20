package ru.rightcode.arm.exceptions;

public class PatientNotFoundException extends RuntimeException {

    public PatientNotFoundException(Long patientCode) {
        super(String.format("Пациент с кодом %d не найден", patientCode));
    }
}
