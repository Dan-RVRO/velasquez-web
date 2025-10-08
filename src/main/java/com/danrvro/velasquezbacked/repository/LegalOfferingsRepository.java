package com.danrvro.velasquezbacked.repository;

import com.danrvro.velasquezbacked.entity.LegalOfferingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de persistencia para LegalOfferingsEntity.
 *
 * Métodos heredados más relevantes de JpaRepository:
 * - save()              : persiste o actualiza una entidad.
 * - findAllById()       : obtiene entidades por ids.
 * - delete()            : elimina la entidad proporcionada.
 * - findAll()           : obtiene todas ordenadas.
 */

@Repository
public interface LegalOfferingsRepository extends JpaRepository<LegalOfferingEntity, Long> {
    // custom query here
}