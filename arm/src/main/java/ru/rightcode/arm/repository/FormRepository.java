package ru.rightcode.arm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.rightcode.arm.model.Form;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {

    @Query("select exists(select 1 from Form f where f.id = :formId)")
    boolean checkIfExists(@Param("formId") Long formId);

}