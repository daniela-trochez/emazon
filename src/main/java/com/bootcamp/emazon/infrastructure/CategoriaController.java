package com.bootcamp.emazon.infrastructure;

import com.bootcamp.emazon.application.CrearCategoriaUseCase;
import com.bootcamp.emazon.domain.Categoria;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    private final CrearCategoriaUseCase crearCategoriaUseCase;

    // Inyección de dependencias a través del constructor
    public CategoriaController(CrearCategoriaUseCase crearCategoriaUseCase) {
        this.crearCategoriaUseCase = crearCategoriaUseCase;
    }


    @PostMapping
    public ResponseEntity<CategoriaDTO> crearCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        Categoria categoria = crearCategoriaUseCase.ejecutar(categoriaDTO.getNombre(), categoriaDTO.getDescripcion());
        CategoriaDTO responseDTO = new CategoriaDTO(categoria.getId(), categoria.getNombre(), categoria.getDescripcion());
        return ResponseEntity.ok(responseDTO);
    }
}
