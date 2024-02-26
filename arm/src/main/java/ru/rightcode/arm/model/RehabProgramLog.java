package ru.rightcode.arm.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
//import ru.rightcode.arm.listener.RehabProgramRevisionListener;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "rehab_program_log")
//@RevisionEntity(RehabProgramRevisionListener.class)
public class RehabProgramLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn(name = "rehab_program_id")
    private Long rehabProgramId;

    @Column(name = "who_changed")
    private String whoChanged;

    @Column(name = "change_date")
    private LocalDate changeDate;

    @Column(name = "change", length = Integer.MAX_VALUE)
    private String change;

    @Column(name = "operation", length = Integer.MAX_VALUE)
    private String operation;

}
