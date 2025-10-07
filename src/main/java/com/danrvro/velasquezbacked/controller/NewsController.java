package com.danrvro.velasquezbacked.controller;

import com.danrvro.velasquezbacked.entity.ContentStatus;
import com.danrvro.velasquezbacked.entity.NewsEntity;
import com.danrvro.velasquezbacked.service.NewsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para exponer operaciones CRUD sobre NewsEntity.
 *
 * Notas de diseño:
 * - Respuestas usan ResponseEntity para controlar códigos HTTP explícitos.
 * - No incluye manejo de excepciones global; se recomienda agregar un @ControllerAdvice para mapear
 *
 */
@Validated
@RestController
@RequestMapping("/api/news")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public List<NewsEntity> getAll() {
        return newsService.findAll();
    }

    /**
     * GET /api/news?status=...
     * Listar noticias por estado.
     * status es requerido como query param; si quieres hacerlo opcional, cambia required = false y gestiona null.
     */
    @GetMapping("/status")
    public ResponseEntity<List<NewsEntity>> listByStatus(@RequestParam("status") ContentStatus status) {
        List<NewsEntity> list = newsService.listByStatus(status);
        return ResponseEntity.ok(list);
    }

    /**
     * GET /api/news/search?text=...
     * Búsqueda por texto en título o resumen.
     */
    @GetMapping("/search")
    public ResponseEntity<List<NewsEntity>> searchByText(@RequestParam("text") String text) {
        List<NewsEntity> results = newsService.searchByText(text);
        return ResponseEntity.ok(results);
    }

    /**
     * GET /api/news/{id}
     * Obtener una noticia por id.
     * Devuelve 200 con la entidad o deja propagar excepción si no existe (mapea con ControllerAdvice).
     */
    @GetMapping("/{id}")
    public ResponseEntity<NewsEntity> getById(@PathVariable("id") Long id) {
        NewsEntity newsEntity = newsService.getById(id);
        return ResponseEntity.ok(newsEntity);
    }

    /**
     * POST /api/newsEntity
     * Crear una nueva noticia.
     * Devuelve 201 Created con la entidad creada.
     */
    @PostMapping
    public ResponseEntity<NewsEntity> create(@RequestBody NewsEntity newsEntity) {
        NewsEntity created = newsService.create(newsEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * PUT /api/news/{id}
     * Actualizar una noticia completa.
     */
    @PutMapping("/{id}")
    public ResponseEntity<NewsEntity> update(@PathVariable("id") Long id, @RequestBody NewsEntity updated) {
        NewsEntity saved = newsService.update(id, updated);
        return ResponseEntity.ok(saved);
    }

    /**
     * DELETE /api/news/{id}
     * Eliminar por id.
     * Devuelve 204 No Content si la eliminación fue exitosa.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        newsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
