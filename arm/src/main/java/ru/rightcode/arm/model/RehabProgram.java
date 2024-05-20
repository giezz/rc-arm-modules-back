package ru.rightcode.arm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.time.Instant;
import java.util.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "rehab_program", schema = "arm")
public class RehabProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @NotNull
    @Column(name = "is_current", nullable = false)
    private Boolean isCurrent = false;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "end_date")
    private Instant endDate;

    @OneToMany(mappedBy = "rehabProgram", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Module> modules = new ArrayList<>();

    @OneToMany(mappedBy = "rehabProgram", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProgramForm> forms = new ArrayList<>();

    @OneToMany(mappedBy = "rehabProgram", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Protocol> protocols = new ArrayList<>();

    public void addModule(Module module) {
        modules.add(module);
        module.setRehabProgram(this);
    }

    public void deleteModule(Module module) {
        modules.remove(module);
        module.setRehabProgram(null);
    }

    public void addForm(ProgramForm form) {
        forms.add(form);
        form.setRehabProgram(this);
    }

    public void deleteFom(ProgramForm form) {
        forms.remove(form);
        form.setRehabProgram(null);
    }

    public void addProtocol(Protocol protocol) {
        protocols.add(protocol);
        protocol.setRehabProgram(this);
    }

    public void setDoctorById(Long id) {
        Doctor doc = new Doctor();
        doc.setId(id);
        doctor = doc;
    }

    public boolean canDoctorEdit(Long doctorId) {
        return this.getDoctor().getId().equals(doctorId) && this.getIsCurrent();
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        RehabProgram that = (RehabProgram) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

}
