package com.bootcamp.emazon.application;

import com.bootcamp.emazon.domain.Categoria;
import com.bootcamp.emazon.domain.CategoriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CrearCategoriaUseCaseTest {

    private CategoriaRepository categoriaRepository;
    private CrearCategoriaUseCase crearCategoriaUseCase;

    @BeforeEach
    void setUp() {
        categoriaRepository = mock(CategoriaRepository.class);
        crearCategoriaUseCase = new CrearCategoriaUseCase(categoriaRepository);
    }

    @Test
    void deberiaCrearCategoriaCuandoNombreYDescripcionSonValidos() {
        // Arrange
        String nombre = "Electrónica";
        String descripcion = "Artículos electrónicos y gadgets";
        // Configurar el mock para devolver false cuando se llame a existsByNombre
        when(categoriaRepository.existsByNombre(nombre)).thenReturn(false);

        // Act
        Categoria result = crearCategoriaUseCase.ejecutar(nombre, descripcion);

        // Assert
        assertNotNull(result);
        assertEquals(nombre, result.getNombre());
        assertEquals(descripcion, result.getDescripcion());
        verify(categoriaRepository, times(1)).save(any(Categoria.class));
    }

    @Test
    void deberiaLanzarExcepcionCuandoNombreEsDuplicado() {
        // Arrange
        String nombre = "Electrónica";
        String descripcion = "Artículos electrónicos y gadgets";
        when(categoriaRepository.existsByNombre(nombre)).thenReturn(true);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            crearCategoriaUseCase.ejecutar(nombre, descripcion);
        });
        assertEquals("El nombre de la categoría ya existe", exception.getMessage());
        verify(categoriaRepository, never()).save(any(Categoria.class));
    }

    @Test
    void deberiaLanzarExcepcionCuandoNombreEsDemasiadoLargo() {
        // Arrange
        String nombre = "Nombre de categoría extremadamente largo que supera los 50 caracteres";
        String descripcion = "Descripción válida";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            crearCategoriaUseCase.ejecutar(nombre, descripcion);
        });
        assertEquals("Tamaño máximo excedido", exception.getMessage());
        verify(categoriaRepository, never()).save(any(Categoria.class));
    }

    @Test
    void deberiaLanzarExcepcionCuandoDescripcionEsDemasiadoLarga() {
        // Arrange
        String nombre = "Nombre válido";
        String descripcion = "Descripción extremadamente larga que supera los 90 caracteres. Esto debería causar una excepción.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            crearCategoriaUseCase.ejecutar(nombre, descripcion);
        });
        assertEquals("Tamaño máximo excedido", exception.getMessage());
        verify(categoriaRepository, never()).save(any(Categoria.class));
    }


}
