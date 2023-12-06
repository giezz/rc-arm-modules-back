package ru.rightcode.medcart.service.patient;


import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;
import ru.rightcode.medcart.model.Patient;

import java.util.List;

@WebService(name = "PatientWebService")
public interface PatientService {

    @WebMethod
    @RequestWrapper(localName = "getAllPatientsRequest", className = "ru.rightcode.medcart.service.patient.GetAllPatientsRequest")
    @ResponseWrapper(localName = "getAllPatientsResponse", className = "ru.rightcode.medcart.service.patient.GetAllPatientsResponse")
    List<Patient> getAllPatients();

    @WebMethod
    @RequestWrapper(localName = "getPatientByCodeRequest", className = "ru.rightcode.medcart.service.patient.GetPatientByCodeRequest")
    @ResponseWrapper(localName = "getPatientByCodeResponse", className = "ru.rightcode.medcart.service.patient.GetPatientByCodeResponse")
    Patient getPatientByCode(@WebParam(name = "code")  Long code);
}
