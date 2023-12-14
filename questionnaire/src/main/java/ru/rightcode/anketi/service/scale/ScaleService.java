package ru.rightcode.anketi.service.scale;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;
import ru.rightcode.anketi.model.Scale;

import java.util.List;

@WebService(name = "ScaleService")
public interface ScaleService {

    @WebMethod
    @RequestWrapper(localName = "getScalesRequest", className = "ru.rightcode.anketi.service.scale.GetScalesRequest")
    @ResponseWrapper(localName = "getScalesResponse", className = "ru.rightcode.anketi.service.scale.GetScalesResponse")
    List<Scale> getScales();

}
