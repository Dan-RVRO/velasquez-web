package com.danrvro.velasquezbacked.service;

import com.danrvro.velasquezbacked.entity.LegalOfferingEntity;
import com.danrvro.velasquezbacked.entity.LegalOfferingEntity.Status;
import com.danrvro.velasquezbacked.entity.NewsEntity;
import com.danrvro.velasquezbacked.repository.LegalOfferingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Servicio CRUD para LegalOfferingEntity.
 *

 * Responsabilidades aquí:
 * - Orquestar operaciones básicas del repositorio.
 * - Realizar comprobaciones mínimas (null checks) para evitar NPEs triviales.
 */

@Service
@RequiredArgsConstructor
public class LegalOfferingService {

    private final LegalOfferingsRepository repository;

    /**
     * Recupera todas las ofertas legales.
     * No realiza transformaciones ni filtrados.
     */
    @Transactional(readOnly = true)
    public List<LegalOfferingEntity> findAll() {
        return repository.findAll();
    }


    /**
     * Recupera una oferta por id. Devuelve Optional en lugar de lanzar excepción.
     * El controlador o el manejador global decide la respuesta ante ausencia.
     */
    @Transactional(readOnly = true)
    public Optional<LegalOfferingEntity> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return repository.findById(id);
    }

    /**
     * Crea una nueva oferta.
     * Se asegura solo que el objeto input no sea null.
     */
    @Transactional
    public LegalOfferingEntity create(LegalOfferingEntity input) {
        if (input == null) {
            throw new IllegalArgumentException("input cannot be null"); // dejar propagar para el manejador central
        }
        if (input.getStatus() == null) {
            input.setStatus(Status.DRAFT);
        }
        return repository.save(input);
    }

    /**
     * Actualiza parcialmente una oferta existente.
     * Devuelve Optional con la entidad actualizada si existía; Optional.empty() si no.
     * No atrapa ni transforma excepciones; errores de concurrencia o de BD se propagan.
     */
    @Transactional
    public Optional<LegalOfferingEntity> updatePartial(Long id, LegalOfferingEntity updates) {
        if (id == null || updates == null) {
            return Optional.empty();
        }

        return repository.findById(id).map(existing -> {
            if (updates.getTitle() != null) {
                existing.setTitle(updates.getTitle().trim());
            }
            if (updates.getDescription() != null) {
                existing.setDescription(updates.getDescription().trim());
            }
            if (updates.getStatus() != null) {
                existing.setStatus(updates.getStatus());
            }
            return repository.save(existing);
        });
    }

    /**
     * Elimina por id. Devuelve true si el id existía y se solicitó eliminación; false si no existía.
     * No lanza excepción si el id no existe; cualquier excepción de BD se deja propagar.
     */
    @Transactional
    public boolean deleteByIdIfExists(Long id) {
        if (id == null) {
            return false;
        }
        boolean exists = repository.existsById(id);
        if (!exists) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }

}