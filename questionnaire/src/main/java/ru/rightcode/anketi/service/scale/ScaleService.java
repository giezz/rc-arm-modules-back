package ru.rightcode.anketi.service.scale;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;
import ru.rightcode.anketi.model.Scale;

import java.util.List;

@WebService(name = "ScaleService")
public interface ScaleService {

    @WebMethod
    @RequestWrapper(localName = "getScalesRequest",
            className = "ru.rightcode.anketi.service.scale.GetScalesRequest")
    @ResponseWrapper(localName = "getScalesResponse",
            className = "ru.rightcode.anketi.service.scale.GetScalesResponse")
    List<Scale> getScales();

//    @WebMethod
//    @RequestWrapper(localName = "getScaleByIdRequest", className = "ru.rightcode.anketi.service.scale.GetScaleByIdRequest")
//    @ResponseWrapper(localName = "getScaleByIdResponse", className = "ru.rightcode.anketi.service.scale.GetScaleByIdResponse")
//    List<Scale> getScaleById(@WebParam(name = "id") Long id);
//
//    @WebMethod
//    @RequestWrapper(localName = "getScaleByNameRequest", className = "ru.rightcode.anketi.service.scale.GetScaleByNameRequest")
//    @ResponseWrapper(localName = "getScaleByNameResponse", className = "ru.rightcode.anketi.service.scale.GetScaleByNameResponse")
//    List<Scale> getScaleByName(@WebParam(name = "name") String name);
}
