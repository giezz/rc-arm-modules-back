package ru.rightcode.anketi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "interpretation", schema = "anketi")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Interpretation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "min_value", nullable = false, precision = 100, scale = 2)
    private BigDecimal minValue;

    @NotNull
    @Column(name = "max_value", nullable = false, precision = 100, scale = 2)
    private BigDecimal maxValue;

    @NotNull
    @Column(name = "description", nullable = false, length = Integer.MAX_VALUE)
    private String description;

    @NotNull
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "scale_id", nullable = false)
    private Scale scale;
}