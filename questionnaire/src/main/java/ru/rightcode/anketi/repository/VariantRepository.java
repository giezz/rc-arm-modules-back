package ru.rightcode.anketi.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.rightcode.anketi.model.Variant;

import java.util.List;
import java.util.Optional;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Long> {
    @Override
    @EntityGraph(attributePaths = {"question"})
    @NonNull
    Optional<Variant> findById(@NonNull Long id);

    @Override
    @EntityGraph(attributePaths = {})
    @NonNull
    List<Variant> findAll();

    @Modifying
    @Query("delete from Variant v where v.question.id =?1")
    void deleteByQuestion_id(Long questionId);
}
