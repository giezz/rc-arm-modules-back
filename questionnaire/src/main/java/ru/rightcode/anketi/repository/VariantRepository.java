package ru.rightcode.anketi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.rightcode.anketi.model.Variant;

import java.util.List;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Long> {
    @Query("select v from Variant v where v.question_id.id = ?1")
    List<Variant> findAllByQuestion_id(Long questionId);
}
