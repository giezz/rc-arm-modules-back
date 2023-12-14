package ru.rightcode.anketi.service.answer;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;
import ru.rightcode.anketi.model.Answer;

import java.util.List;

@WebService(name = "AnswerService")
public interface AnswerService {

    @WebMethod
    @RequestWrapper(localName = "getAnswersRequest", className = "ru.rightcode.anketi.service.answer.GetAnswersRequest")
    @ResponseWrapper(localName = "getAnswersResponse", className = "ru.rightcode.anketi.service.answer.GetAnswersResponse")
    List<Answer> getAnswers(@WebParam(name = "patientCode") Long patientCode);
}
