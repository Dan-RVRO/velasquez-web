package com.danrvro.velasquezbacked.service;

import com.danrvro.velasquezbacked.entity.PartnersEntity;
import com.danrvro.velasquezbacked.repository.PartnersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for PartnersEntity.
 *
 * Responsibilities:
 *  - Coordinate repository operations for partners
 *  - Provide a clean API for controllers and other services
 *  - Keep operations transactional where state changes occur
 */
@Service
@RequiredArgsConstructor
public class PartnersService {

    private final PartnersRepository repository;

    /**
     * Create and persist a new partner.
     *
     * @param partner entity to create
     * @return persisted partner with id populated
     */
    @Transactional
    public PartnersEntity create(PartnersEntity partner) {
        return repository.save(partner);
    }

    /**
     * Update an existing partner.
     *
     * Behavior: caller should provide an entity with an existing id to update.
     * This method saves the provided entity state to the database.
     *
     * @param partner entity with updated state
     * @return saved partner
     */
    @Transactional
    public PartnersEntity update(PartnersEntity partner) {
        return repository.save(partner);
    }

    /**
     * Find a partner by id.
     *
     * @param id primary key
     * @return Optional containing partner if found
     */
    @Transactional(readOnly = true)
    public Optional<PartnersEntity> findById(Long id) {
        return repository.findById(id);
    }

    /**
     * Retrieve all partners.
     *
     * @return list of partners
     */
    @Transactional(readOnly = true)
    public List<PartnersEntity> findAll() {
        return repository.findAll();
    }

    /**
     * Find partners by exact name.
     *
     * @param name exact name to search
     * @return list of partners matching name
     */
    @Transactional(readOnly = true)
    public List<PartnersEntity> findByName(String name) {
        return repository.findByName(name);
    }

    /**
     * Delete partner by id.
     *
     * Note: physical delete; if soft-delete is required, modify to set a flag instead.
     *
     * @param id partner id
     */
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}