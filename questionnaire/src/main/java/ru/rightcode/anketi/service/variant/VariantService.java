package ru.rightcode.anketi.service.variant;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;
import ru.rightcode.anketi.model.Variant;

import java.util.List;

@WebService(name = "VariantService")
public interface VariantService {

    @WebMethod
    @RequestWrapper(localName = "getVariantsRequest", className = "ru.rightcode.anketi.service.variant.GetVariantsRequest")
    @ResponseWrapper(localName = "getVariantsResponse", className = "ru.rightcode.anketi.service.variant.GetVariantsResponse")
    List<Variant> getVariants();
}
