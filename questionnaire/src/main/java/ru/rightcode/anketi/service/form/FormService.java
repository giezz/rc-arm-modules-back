package ru.rightcode.anketi.service.form;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;
import ru.rightcode.anketi.DTO.FormDTO;
import ru.rightcode.anketi.model.Form;

import java.util.List;

@WebService(name = "FormService")
public interface FormService {

    @WebMethod
    @RequestWrapper(localName = "getFormsRequest",
            className = "ru.rightcode.anketi.service.form.GetFormsRequest")
    @ResponseWrapper(localName = "getFormsResponse",
            className = "ru.rightcode.anketi.service.form.GetFormsResponse")
    List<FormDTO> getAllForms();

    @WebMethod
    @RequestWrapper(localName = "getFormByNameRequest",
            className = "ru.rightcode.anketi.service.form.GetFormByNameRequest")
    @ResponseWrapper(localName = "getFormByNameResponse",
            className = "ru.rightcode.anketi.service.form.GetFormByNameResponse")
    List<FormDTO> getFormByName(String name);

    @WebMethod
    @RequestWrapper(localName = "getFormByIdRequest",
            className = "ru.rightcode.anketi.service.form.GetFormByIdRequest")
    @ResponseWrapper(localName = "getFormByIdResponse",
            className = "ru.rightcode.anketi.service.form.GetFormByIdResponse")
    FormDTO getFormById(Long id);

    @WebMethod
    @RequestWrapper(localName = "createFormRequest",
            className = "ru.rightcode.anketi.service.form.CreateFormRequest")
    @ResponseWrapper(localName = "createFormResponse",
            className = "ru.rightcode.anketi.service.form.CreateFormResponse")
    FormDTO createForm(FormDTO form);

    @WebMethod
    @RequestWrapper(localName = "updateFormRequest",
            className = "ru.rightcode.anketi.service.form.UpdateFormRequest")
    @ResponseWrapper(localName = "updateFormResponse",
            className = "ru.rightcode.anketi.service.form.UpdateFormResponse")
    FormDTO updateForm(FormDTO form);

    @WebMethod
    @RequestWrapper(localName = "deleteFormRequest",
            className = "ru.rightcode.anketi.service.form.DeleteFormRequest")
    @ResponseWrapper(localName = "deleteFormResponse",
            className = "ru.rightcode.anketi.service.form.DeleteFormResponse")
    void deleteForm(Long id);
}
