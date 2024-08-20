package com.bootcamp.emazon.infrastructure;

import com.bootcamp.emazon.domain.Categoria;
import com.bootcamp.emazon.domain.CategoriaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl implements CategoriaRepository {
    private final JpaCategoriaRepository jpaCategoriaRepository;

    // Inyección de dependencias a través del constructor
    public CategoriaServiceImpl(JpaCategoriaRepository jpaCategoriaRepository) {
        this.jpaCategoriaRepository = jpaCategoriaRepository;
    }

    @Override
    public Categoria save(Categoria categoria) {
        CategoriaEntity entity = new CategoriaEntity();
        entity.setNombre(categoria.getNombre());
        entity.setDescripcion(categoria.getDescripcion());
        CategoriaEntity savedEntity = jpaCategoriaRepository.save(entity);
        return new Categoria(savedEntity.getId(), savedEntity.getNombre(), savedEntity.getDescripcion());
    }

    @Override
    public boolean existsByNombre(String nombre) {
        return jpaCategoriaRepository.findByNombre(nombre).isPresent();
    }
}
