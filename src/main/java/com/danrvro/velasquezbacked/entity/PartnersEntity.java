package com.danrvro.velasquezbacked.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "partners")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class PartnersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    // Basic
    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT", length = 600)
    private String bio;

    // Credentials / background
    @ElementCollection
    @CollectionTable(name = "partner_education", joinColumns = @JoinColumn(name = "partner_id"))
    @Column(name = "education")
    private List<String> education;

    @Column(name = "years_experience", nullable = false)
    private Integer yearsExperience = 0;

    @ElementCollection
    @CollectionTable(name = "partner_practice_areas", joinColumns = @JoinColumn(name = "partner_id"))
    @Column(name = "practice_area")
    private List<String> practiceAreas;

    @ElementCollection
    @CollectionTable(name = "partner_certifications", joinColumns = @JoinColumn(name = "partner_id"))
    @Column(name = "certification")
    private List<String> certification;

    // -------------------------------

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status = Status.BORRADOR;

    public enum Status {
        INACTIVO, ACTIVO, BORRADOR
    }
    // -------------------------------
}