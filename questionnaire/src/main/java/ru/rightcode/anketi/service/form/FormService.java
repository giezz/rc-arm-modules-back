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
    @RequestWrapper(localName = "getFormsRequest",
            className = "ru.rightcode.anketi.service.form.GetFormsRequest")
    @ResponseWrapper(localName = "getFormsResponse",
            className = "ru.rightcode.anketi.service.form.GetFormsResponse")
    List<Form> getForms();

    @WebMethod
    @RequestWrapper(localName = "getFormByNameRequest",
            className = "ru.rightcode.anketi.service.form.GetFormByNameRequest")
    @ResponseWrapper(localName = "getFormByNameResponse",
            className = "ru.rightcode.anketi.service.form.GetFormByNameResponse")
    List<Form> getFormByName(String name);

    @WebMethod
    @RequestWrapper(localName = "getFormByIdRequest",
            className = "ru.rightcode.anketi.service.form.GetFormByIdRequest")
    @ResponseWrapper(localName = "getFormByIdResponse",
            className = "ru.rightcode.anketi.service.form.GetFormByIdResponse")
    List<Form> getFormById(Long id);
}
