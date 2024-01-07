package ru.rightcode.anketi.service.form;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;
import ru.rightcode.anketi.dto.FormDto;

import java.util.List;

@WebService(name = "FormService")
public interface FormService {

    @WebMethod
    @RequestWrapper(localName = "getFormsRequest",
            className = "ru.rightcode.anketi.service.form.GetFormsRequest")
    @ResponseWrapper(localName = "getFormsResponse",
            className = "ru.rightcode.anketi.service.form.GetFormsResponse")
    List<FormDto> getAllForms();

    @WebMethod
    @RequestWrapper(localName = "getFormByNameRequest",
            className = "ru.rightcode.anketi.service.form.GetFormByNameRequest")
    @ResponseWrapper(localName = "getFormByNameResponse",
            className = "ru.rightcode.anketi.service.form.GetFormByNameResponse")
    List<FormDto> getFormByName(String name);

    @WebMethod
    @RequestWrapper(localName = "getFormByIdRequest",
            className = "ru.rightcode.anketi.service.form.GetFormByIdRequest")
    @ResponseWrapper(localName = "getFormByIdResponse",
            className = "ru.rightcode.anketi.service.form.GetFormByIdResponse")
    FormDto getFormById(Long id);

    @WebMethod
    @RequestWrapper(localName = "createFormRequest",
            className = "ru.rightcode.anketi.service.form.CreateFormRequest")
    @ResponseWrapper(localName = "createFormResponse",
            className = "ru.rightcode.anketi.service.form.CreateFormResponse")
    FormDto createForm(FormDto form);

    @WebMethod
    @RequestWrapper(localName = "updateFormRequest",
            className = "ru.rightcode.anketi.service.form.UpdateFormRequest")
    @ResponseWrapper(localName = "updateFormResponse",
            className = "ru.rightcode.anketi.service.form.UpdateFormResponse")
    FormDto updateForm(FormDto form);

    @WebMethod
    @RequestWrapper(localName = "deleteFormRequest",
            className = "ru.rightcode.anketi.service.form.DeleteFormRequest")
    @ResponseWrapper(localName = "deleteFormResponse",
            className = "ru.rightcode.anketi.service.form.DeleteFormResponse")
    void deleteForm(Long id);
}
