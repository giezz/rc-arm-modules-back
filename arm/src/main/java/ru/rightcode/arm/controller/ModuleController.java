package ru.rightcode.arm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rightcode.arm.service.ModuleService;

@RestController
@RequestMapping("api/v1/modules")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ModuleController {

    private final ModuleService moduleService;

//    @PostMapping("/create")
//    public ResponseEntity<?> create(@RequestBody ModuleRequest request) {
//        moduleService.create(request.getModuleName(), request.getRehabProgramId());
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }

}
