package ru.rightcode.arm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rightcode.arm.model.Doctor;
import ru.rightcode.arm.service.DoctorService;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/doctors")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DoctorController {

    private final DoctorService doctorService;

//    @GetMapping("/my-patients")
//    public ResponseEntity<?> getMyPatients(Principal principal) {
//        Doctor doctor = doctorService.getByLogin(principal.getName());
//        return ResponseEntity.ok(
//                doctorService.getPatients(doctor)
//        );
//    }
}
