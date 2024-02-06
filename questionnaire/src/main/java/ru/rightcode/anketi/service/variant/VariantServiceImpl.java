package ru.rightcode.anketi.service.variant;

import jakarta.jws.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rightcode.anketi.model.Variant;
import ru.rightcode.anketi.repository.VariantRepository;

import java.util.List;

@Service
@WebService
@RequiredArgsConstructor
public class VariantServiceImpl implements VariantService {

    @Autowired
    private final VariantRepository variantRepository;


    @Override
    public List<Variant> getVariants() {
        return variantRepository.findAll();
    }
}
