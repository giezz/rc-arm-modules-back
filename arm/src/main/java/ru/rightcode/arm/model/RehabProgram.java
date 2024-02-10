package ru.rightcode.arm.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "rehab_program", schema = "doc")
@ToString
public class RehabProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    @ToString.Exclude
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    @ToString.Exclude
    private Doctor doctor;

    @Column(name = "is_current", nullable = false)
    private Boolean isCurrent = false;

    @Column(name = "creation_date")
    private Instant creationDate;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "start_form_id")
//    @ToString.Exclude
    private Form startForm;

    @ManyToOne
    @JoinColumn(name = "end_form_id")
//    @ToString.Exclude
    private Form endForm;

    @OneToMany(mappedBy = "rehabProgram", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    @ToString.Exclude
    private List<Module> modules = new ArrayList<>();

    public void addModule(Module module) {
        modules.add(module);
        module.setRehabProgram(this);
    }

    public void addAllModules(List<Module> m) {
        for (Module module : m) {
            modules.add(module);
            module.setRehabProgram(this);
        }
    }

    public void setDoctorById(Long id) {
        Doctor doc = new Doctor();
        doc.setId(id);
        doctor = doc;
    }
}