package com.danrvro.velasquezbacked.service;

import com.danrvro.velasquezbacked.entity.ContentStatus;
import com.danrvro.velasquezbacked.entity.NewsEntity;
import com.danrvro.velasquezbacked.repository.NewsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class NewsService {

    private final NewsRepository newsRepository;

    // READ ALL
    public List<NewsEntity> findAll() {
        return newsRepository.findAll();
    }

    // Constructor con inyección de dependencias (Spring lo gestiona automáticamente)
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }


    /**
     * Obtener una noticia por su ID.
     * Si no existe, lanza una excepción estándar (puedes personalizarla si lo deseas).
     */
    @Transactional(readOnly = true)
    public NewsEntity getById(Long id) {
        return newsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("NewsEntity not found with id: " + id));
    }

    /**
     * Crear una nueva noticia.
     * Se puede aplicar lógica adicional aquí si se requiere (ej. valores por defecto).
     */
    @Transactional
    public NewsEntity create(NewsEntity newsEntity) {
        if (newsEntity.getStatus() == null) {
            newsEntity.setStatus(ContentStatus.BORRADOR); // Valor por defecto si no se especifica
        }
        return newsRepository.save(newsEntity);
    }

    /**
     * Actualizar una noticia existente.
     * Se sobrescriben todos los campos relevantes.
     */
    @Transactional
    public NewsEntity update(Long id, NewsEntity updated) {
        NewsEntity existing = newsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("NewsEntity not found with id: " + id));

        // Actualización de campos
        existing.setTitle(updated.getTitle());
        existing.setSummary(updated.getSummary());
        existing.setContent(updated.getContent());
        existing.setPublicationDate(updated.getPublicationDate());
        existing.setAuthor(updated.getAuthor());
        existing.setCategory(updated.getCategory());
        existing.setStatus(updated.getStatus());

        return newsRepository.save(existing);
    }

    /**
     * Eliminar una noticia por ID.
     * Se verifica existencia antes de eliminar para evitar errores silenciosos.
     */
    @Transactional
    public void delete(Long id) {
        if (!newsRepository.existsById(id)) {
            throw new IllegalArgumentException("NewsEntity not found with id: " + id);
        }
        newsRepository.deleteById(id);
    }
}