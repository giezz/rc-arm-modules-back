package ru.rightcode.anketi.service.question;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;
import ru.rightcode.anketi.model.Question;

import java.util.List;

@WebService(name = "QuestionService")
public interface QuestionService {

    @WebMethod
    @RequestWrapper(localName = "getQuestionsRequest", className = "ru.rightcode.anketi.service.question.GetQuestionsRequest")
    @ResponseWrapper(localName = "getQuestionsResponse", className = "ru.rightcode.anketi.service.question.GetQuestionsResponse")
    List<Question> getQuestions();
}
