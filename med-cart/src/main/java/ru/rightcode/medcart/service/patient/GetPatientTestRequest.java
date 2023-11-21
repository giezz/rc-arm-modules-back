package ru.rightcode.medcart.service.patient;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPatientTestRequest")
@XmlRootElement(name = "GetPatientTestRequest")
@Getter
@Setter
public class GetPatientTestRequest {

    @XmlElement
    private Long patientCode;

    @XmlElement
    String firstName;
}
