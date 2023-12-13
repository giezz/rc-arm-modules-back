package ru.rightcode.anketi.service.form;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;
import ru.rightcode.anketi.model.Form;

import java.util.List;

@WebService(name = "FormService")
public interface FormService {

    @WebMethod
    @RequestWrapper(localName = "getFormsRequest", className = "ru.rightcode.doc.service.form.GetFormsRequest")
    @ResponseWrapper(localName = "getFormsResponse", className = "ru.rightcode.doc.service.form.GetFormsResponse")
    List<Form> getForms();
}
