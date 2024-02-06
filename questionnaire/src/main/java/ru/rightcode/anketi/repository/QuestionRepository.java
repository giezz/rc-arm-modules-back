package ru.rightcode.anketi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.anketi.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

}
