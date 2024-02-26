package ru.rightcode.anketi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcode.anketi.model.Scale;

import java.util.List;

@Repository
public interface ScaleRepository extends JpaRepository<Scale, Long> {
    List<Scale> findAllByName(String name);

    List<Scale> findAllById(Long id);
}
