package ru.rightcde.icf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rightcde.icf.model.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    List<Category> findByCode(String code);
}