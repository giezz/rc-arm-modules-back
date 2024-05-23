package ru.rightcode.patient.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "patient", schema = "arm", indexes = {
        @Index(name = "patient_code_uq", columnList = "patient_code", unique = true),
        @Index(name = "passport_uq", columnList = "passport_id", unique = true)
})
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "patient_code", nullable = false)
    private Long patientCode;

    @Size(max = 255)
    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Size(max = 255)
    @Column(name = "middle_name")
    private String middleName;

    @Size(max = 255)
    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull
    @Column(name = "gender", nullable = false, length = Integer.MAX_VALUE)
    private String gender;

    @NotNull
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "death_date")
    private LocalDate deathDate;

    @NotNull
    @Column(name = "address", nullable = false, length = Integer.MAX_VALUE)
    private String address;

    @Size(max = 18)
    @NotNull
    @Column(name = "phone_number", nullable = false, length = 18)
    private String phoneNumber;

    @NotNull
    @Column(name = "work_place_data", nullable = false, length = Integer.MAX_VALUE)
    private String workPlaceData;

    @Column(name = "bookmark", length = Integer.MAX_VALUE)
    private String bookmark;

    @Size(max = 11)
    @NotNull
    @Column(name = "snils", nullable = false, length = 11)
    private String snils;

    @Size(max = 16)
    @NotNull
    @Column(name = "polis", nullable = false, length = 16)
    private String polis;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "_user_id", nullable = false)
    @ToString.Exclude
    private User user;

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", nullable = false)
    private PatientStatus patientStatus;

    @NotNull
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "passport_id", nullable = false)
    private Passport passport;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private Set<RehabProgram> rehabPrograms = new HashSet<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Patient patient = (Patient) o;
        return getId() != null && Objects.equals(getId(), patient.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
