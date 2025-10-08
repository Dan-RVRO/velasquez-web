package com.danrvro.velasquezbacked.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad que representa un servicio legal ofrecido por un abogado.
 */

@Entity
@Table(name = "legal_offerings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class LegalOfferingEntity {

    /**
     * Identificador único del servicio.
     * Estrategia IDENTITY delega la generación a la base de datos (autoincrement).
     * Uso de id como parte de Equals and HashCode
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * Nombre corto y descriptivo del servicio.
     * Se limita a 150 caracteres para evitar textos demasiado largos en la BD.
     * No se permite nulo para garantizar que todo servicio tenga un título.
     */
    @Column(nullable = false, length = 150)
    private String title;

    /**
     * Descripción detallada del servicio.
     * Se usa TEXT para permitir almacenamiento de cadenas extensas.
     * No se permite nulo para asegurar que siempre exista una descripción.
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    // -----------------------------------------
    /**
     * Estado actual del servicio.
     * Se almacena como cadena (EnumType.STRING) para mejorar la legibilidad en la BD
     * y evitar problemas si cambia el orden de los valores en el enum.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.DRAFT;

    /**
     * Posibles estados de un servicio legal.
     * Centraliza los valores permitidos y evita el uso de strings "mágicos".
     */
    public enum Status {
        ACTIVE,
        INACTIVE,
        DRAFT
    }
    // ------------------------------------------
}