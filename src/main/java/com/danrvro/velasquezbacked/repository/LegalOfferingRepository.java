package com.danrvro.velasquezbacked.repository;

import com.danrvro.velasquezbacked.entity.LegalOfferingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad LegalOfferingEntity.
 *
 * Extiende JpaRepository para heredar operaciones CRUD:
 * - save(entity)      crea o actualiza
 * - findById(id)      busca por id
 * - findAll()         lista todos
 * - deleteById(id)    elimina por id
 */

@Repository
public interface LegalOfferingRepository extends JpaRepository<LegalOfferingEntity, Long> {

}