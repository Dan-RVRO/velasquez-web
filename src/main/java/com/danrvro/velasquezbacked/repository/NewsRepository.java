package com.danrvro.velasquezbacked.repository;

import com.danrvro.velasquezbacked.entity.ContentStatus;
import com.danrvro.velasquezbacked.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, Long> {

    // Todos los news por estado, orden determinista por fecha de publicación
    List<NewsEntity> findByStatusOrderByPublicationDateDesc(ContentStatus status);

    // Búsqueda de texto en title o summary
    List<NewsEntity> findByTitleContainingIgnoreCaseOrSummaryContainingIgnoreCase(String title, String summary);
}