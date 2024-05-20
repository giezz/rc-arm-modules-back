package ru.rightcde.icf.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rightcde.icf.model.Category;
import ru.rightcde.icf.repository.CategoryRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAllRoots() {
        return categoryRepository.findAllByParentIsNull();
    }

    public List<Category> getByParentCode(String code) {
        return categoryRepository.findByParentCode(code);
    }
}
