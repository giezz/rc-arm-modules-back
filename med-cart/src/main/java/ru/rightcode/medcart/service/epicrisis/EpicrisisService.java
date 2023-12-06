package ru.rightcode.medcart.service.epicrisis;


import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;
import ru.rightcode.medcart.model.Epicrisis;

import java.util.List;

@WebService(name = "EpicrisisService")
public interface EpicrisisService {

    @WebMethod
    @RequestWrapper(localName = "getEpicrisisesRequest", className = "ru.rightcode.medcart.service.epicrisis.GetEpicrisisesRequest")
    @ResponseWrapper(localName = "getEpicrisisesResponse", className = "ru.rightcode.medcart.service.epicrisis.GetEpicrisisesResponse")
    List<Epicrisis> getEpicrisises(@WebParam(name = "patientCode") Long patientCode);


}
