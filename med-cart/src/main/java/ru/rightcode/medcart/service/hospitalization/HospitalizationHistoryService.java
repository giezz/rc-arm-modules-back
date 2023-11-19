package ru.rightcode.medcart.service.hospitalization;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;
import ru.rightcode.medcart.model.HospitalizationHistory;

import java.util.List;

@WebService(name = "HospitalizationHistoryService")
public interface HospitalizationHistoryService {

    @WebMethod
    @RequestWrapper(localName = "getHistoryRequest", className = "ru.rightcode.medcart.service.hospitalization.GetHistoryRequest")
    @ResponseWrapper(localName = "getHistoryResponse", className = "ru.rightcode.medcart.service.hospitalization.GetHistoryResponse")
    List<HospitalizationHistory> getHistory(@WebParam(name = "patientCode") Long patientCode);
}
