package ru.rightcode.patient.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcode.patient.model.Form;
import ru.rightcode.patient.model.ModuleFormAnswer;
import ru.rightcode.patient.model.ProgramFormAnswer;
import ru.rightcode.patient.repository.ModuleFormAnswerRepository;
import ru.rightcode.patient.repository.ModuleFormRepository;
import ru.rightcode.patient.repository.ProgramFormAnswerRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CheckAnswersFormService {
    private final ProgramFormAnswerRepository programFormAnswerRepository;
    private final ModuleFormAnswerRepository moduleFormAnswerRepository;

    @Transactional
    protected List<ProgramFormAnswer> getProgramFormAnswersByProgramFormId(Long id){
        return programFormAnswerRepository.findAllByProgramFormId(id);
    }

    @Transactional
    protected List<ModuleFormAnswer> getModuleFormAnswersByModuleFormId(Long id){
        return moduleFormAnswerRepository.findAllByModuleFormId(id);
    }

    public boolean checkProgram(Long programFormId, Form form){
        List<ProgramFormAnswer> programFormAnswers = getProgramFormAnswersByProgramFormId(programFormId);
//        boolean allMatch = false;
        return programFormAnswers.isEmpty();
//        List<Variant> variants = new ArrayList<>();
//        form.getFormQuestions().forEach(formQuestion  -> {
//            formQuestion.getQuestion().getVariants().forEach(questionVariant  -> {
//                variants = programFormAnswers.stream().map(ProgramFormAnswer::getVariant).filter(variant -> {
//                    return variant.getId().equals(questionVariant.getId());
//                }).toList();
//            });
//        });
    }

    public boolean checkModule(Long moduleFromId){
        return getModuleFormAnswersByModuleFormId(moduleFromId).isEmpty();
    }
}
