package com.bootcamp.emazon.infrastructure;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository

public interface JpaCategoriaRepository extends JpaRepository<CategoriaEntity, Long> {
    Optional<CategoriaEntity> findByNombre(String nombre);
}
