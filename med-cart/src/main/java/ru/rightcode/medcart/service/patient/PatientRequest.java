package ru.rightcode.medcart.service.patient;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "patientRequest")
@Getter
@Setter
public class PatientRequest {

    @XmlElement
    private Long patientCode;

    @XmlElement
    String firstName;


}
