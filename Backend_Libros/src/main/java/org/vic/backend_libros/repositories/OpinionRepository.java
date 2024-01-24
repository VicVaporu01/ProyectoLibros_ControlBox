package org.vic.backend_libros.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vic.backend_libros.models.Opinion;

@Repository
public interface OpinionRepository extends JpaRepository<Opinion, Integer> {
}
