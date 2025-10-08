package com.danrvro.velasquezbacked.repository;

import com.danrvro.velasquezbacked.entity.PartnersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for PartnersEntity.
 *
 * Responsibilities:
 *  Provide basic CRUD and paging operations via JpaRepository.
 *  Expose convenient query methods commonly used by service layers:
 *    - save()              : persiste o actualiza una entidad.
 *    - findAllById()       : obtiene entidades por ids.
 *    - delete()            : elimina la entidad proporcionada.
 *    - findAll()           : obtiene todos
 *
 */
public interface PartnersRepository extends JpaRepository<PartnersEntity, Long> {

    /**
     * Find partners by exact name.
     *
     * @param name exact partner name
     * @return list of partners matching the name
     */
    List<PartnersEntity> findByName(String name);
}