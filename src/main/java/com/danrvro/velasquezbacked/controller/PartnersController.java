package com.danrvro.velasquezbacked.controller;

import com.danrvro.velasquezbacked.entity.PartnersEntity;
import com.danrvro.velasquezbacked.service.PartnersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for PartnersEntity.
 *
 * Responsibilities:
 *  - Expose minimal CRUD endpoints consumed by clients
 *  - Delegate business logic to PartnersService
 *  - Return straightforward HTTP responses; no exception translation is performed here
 *
 * Note: some service methods were removed per request; controller implements only the
 * available service operations (create, update, findById, findAll, findByName, deleteById).
 */
@RestController
@RequestMapping("/api/partners")
@RequiredArgsConstructor
public class PartnersController {

    private final PartnersService partnersService;

    /**
     * Create a new partner.
     * POST /api/partners
     *
     * @param partner payload for creation
     * @return 201 Created with Location header and created entity body
     */
    @PostMapping
    public ResponseEntity<PartnersEntity> create(@RequestBody PartnersEntity partner) {
        PartnersEntity created = partnersService.create(partner);
        URI location = URI.create(String.format("/api/partners/%d", created.getId()));
        return ResponseEntity.created(location).body(created);
    }

    /**
     * Update an existing partner.
     * PUT /api/partners/{id}
     *
     * Behavior: the provided entity is saved as-is; callers should ensure id consistency.
     *
     * @param id path id
     * @param partner payload with updated state
     * @return 200 OK with saved entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<PartnersEntity> update(@PathVariable Long id, @RequestBody PartnersEntity partner) {
        partner.setId(id);
        PartnersEntity saved = partnersService.update(partner);
        return ResponseEntity.ok(saved);
    }

    /**
     * Get partner by id.
     * GET /api/partners/{id}
     *
     * @param id partner id
     * @return 200 OK with partner or 204 No Content when not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<PartnersEntity> getById(@PathVariable Long id) {
        Optional<PartnersEntity> opt = partnersService.findById(id);
        return opt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    /**
     * Retrieve all partners.
     * GET /api/partners
     *
     * @return 200 OK with list (possibly empty)
     */
    @GetMapping
    public ResponseEntity<List<PartnersEntity>> getAll() {
        List<PartnersEntity> list = partnersService.findAll();
        return ResponseEntity.ok(list);
    }

    /**
     * Find partners by exact name.
     * GET /api/partners/search?name=...
     *
     * @param name query parameter
     * @return 200 OK with matching partners
     */
    @GetMapping("/search")
    public ResponseEntity<List<PartnersEntity>> findByName(@RequestParam("name") String name) {
        List<PartnersEntity> list = partnersService.findByName(name);
        return ResponseEntity.ok(list);
    }

    /**
     * Delete partner by id (physical delete).
     * DELETE /api/partners/{id}
     *
     * @param id partner id
     * @return 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        partnersService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}