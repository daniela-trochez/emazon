package com.bootcamp.emazon.application;

import com.bootcamp.emazon.domain.Categoria;
import com.bootcamp.emazon.domain.CategoriaRepository;

public class CrearCategoriaUseCase {

    private final CategoriaRepository categoriaRepository;

    public CrearCategoriaUseCase(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }
    public Categoria ejecutar(String nombre, String descripcion) {
        if (nombre.length() > 50 || descripcion.length() > 90) {
            throw new IllegalArgumentException("Tamaño máximo excedido");
        }
        if (categoriaRepository.existsByNombre(nombre)) {
            throw new IllegalArgumentException("El nombre de la categoría ya existe");
        }
        Categoria categoria = new Categoria(null, nombre, descripcion);
        return categoriaRepository.save(categoria);
    }
}
