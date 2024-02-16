package ru.rightcode.anketi.service.variant;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rightcode.anketi.model.Variant;
import ru.rightcode.anketi.repository.VariantRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VariantServiceImpl  {

    @Autowired
    private final VariantRepository variantRepository;


    public List<Variant> getVariants() {
        return variantRepository.findAll();
    }
}
