package com.danrvro.velasquezbacked.repository;

import com.danrvro.velasquezbacked.entity.ContentStatus;
import com.danrvro.velasquezbacked.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio de persistencia para NewsEntity.
 *
 * Métodos heredados más relevantes de JpaRepository:
 * - save()              : persiste o actualiza una entidad.
 * - findAllById()       : obtiene entidades por ids.
 * - delete()            : elimina la entidad proporcionada.
 * - findAll()           : obtiene todas ordenadas.
*/

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, Long> {

    /**
     * Recupera noticias por su estado ordenadas por fecha de publicación descendente.
     *
     * @param status estado del contenido (por ejemplo PUBLISHED, DRAFT)
     * @return lista de noticias ordenada por publicationDate desc
     */
    List<NewsEntity> findByStatusOrderByPublicationDateDesc(ContentStatus status);

    /**
     * Búsqueda de texto en título o resumen, insensible a mayúsculas.
     *
     * @param title   fragmento buscado en el título
     * @param summary fragmento buscado en el resumen
     * @return lista de noticias que coinciden en title o summary
     */
    List<NewsEntity> findByTitleContainingIgnoreCaseOrSummaryContainingIgnoreCase(String title, String summary);
}