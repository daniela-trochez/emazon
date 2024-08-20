package com.bootcamp.emazon;

import com.bootcamp.emazon.application.CrearCategoriaUseCase;
import com.bootcamp.emazon.infrastructure.CategoriaServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class AppConfig {

    @Bean
    public CrearCategoriaUseCase crearCategoriaUseCase(CategoriaServiceImpl categoriaServiceImpl) {
        return new CrearCategoriaUseCase(categoriaServiceImpl);
    }
}
