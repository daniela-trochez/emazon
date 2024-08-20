package com.bootcamp.emazon.domain;

public interface CategoriaRepository {

    Categoria save(Categoria categoria);
    boolean existsByNombre(String nombre);
}
