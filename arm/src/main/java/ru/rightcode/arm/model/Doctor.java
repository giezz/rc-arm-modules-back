package ru.rightcode.arm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "doctor", schema = "arm", indexes = {
        @Index(name = "doctor_code_uq", columnList = "doctor_code", unique = true)
})
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "doctor_code", nullable = false)
    private Long doctorCode;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "_user_id", nullable = false)
    @ToString.Exclude
    private User user;

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

    @Size(max = 255)
    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Size(max = 18)
    @NotNull
    @Column(name = "phone_number", nullable = false, length = 18)
    private String phoneNumber;

}
