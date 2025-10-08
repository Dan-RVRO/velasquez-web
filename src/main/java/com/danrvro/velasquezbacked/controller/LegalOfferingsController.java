package com.danrvro.velasquezbacked.controller;

import com.danrvro.velasquezbacked.entity.LegalOfferingEntity;
import com.danrvro.velasquezbacked.service.LegalOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador CRUD para LegalOfferingEntity.
 *
 * Diseño:
 * - No se captura ni transforma ninguna excepción en este controlador.
 * - Todas las excepciones y mapeos de error se delegan al módulo centralizado de manejo de errores.
 * - Este controlador expone operaciones básicas: listado, consulta por id, creación,
 *   actualización parcial y eliminación.
 * - Devuelve los objetos tal cual el servicio los retorna (List, Optional, entidad, boolean).
 */

@RestController
@RequestMapping("/api/legalofferings")
@RequiredArgsConstructor
public class LegalOfferingsController {

    private final LegalOfferingService service;

    // todas las ofertas
    @GetMapping
    public List<LegalOfferingEntity> getAll() {
        return service.findAll();
    }

    /**
     * GET /api/legal-offerings/{id}
     *
     * Devuelve Optional<LegalOfferingEntity> tal cual el servicio.
     */
    @GetMapping("/{id}")
    public Optional<LegalOfferingEntity> getById(@PathVariable Long id) {
        return service.findById(id);
    }

    /**
     * POST /api/legal-offerings
     *
     * Crea una nueva oferta y devuelve la entidad persistida.
     * Validación y transformaciones previas deben realizarse fuera de este controlador si se requieren.
     */
    @PostMapping
    public LegalOfferingEntity create(@RequestBody LegalOfferingEntity input) {
        return service.create(input);
    }

    /**
     * PATCH /api/legal-offerings/{id}
     *
     * Actualización parcial: aplica solo campos no nulos del body.
     * Devuelve Optional<LegalOfferingEntity> resultante tal cual lo retorna el servicio.
     */
    @PatchMapping("/{id}")
    public Optional<LegalOfferingEntity> patchUpdate(
            @PathVariable Long id,
            @RequestBody LegalOfferingEntity updates
    ) {
        return service.updatePartial(id, updates);
    }

    /**
     * DELETE /api/legal-offerings/{id}
     *
     * Devuelve boolean indicando si la eliminación fue solicitada sobre un id existente.
     * Cualquier excepción de persistencia se propaga al manejador central.
     */
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return service.deleteByIdIfExists(id);
    }
}