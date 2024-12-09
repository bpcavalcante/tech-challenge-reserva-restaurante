package com.fiap.reservas_restaurantes.config;

import com.fiap.reservas_restaurantes.domain.ports.RestauranteRepositoryPort;
import com.fiap.reservas_restaurantes.domain.usecase.CadastrarRestauranteUseCase;
import com.fiap.reservas_restaurantes.infraestructure.RestauranteJpaRepository;
import com.fiap.reservas_restaurantes.infraestructure.implementations.RestauranteSqlRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringConfig implements WebMvcConfigurer {

  @Bean
  public CadastrarRestauranteUseCase cadastrarRestauranteUseCase(RestauranteRepositoryPort restauranteRepositoryPort) {
    return new CadastrarRestauranteUseCase(restauranteRepositoryPort);
  }

  @Bean
  public RestauranteSqlRepositoryImpl restauranteSqlRepositoryImpl(
      RestauranteJpaRepository restauranteJpaRepository) {
    return new RestauranteSqlRepositoryImpl(restauranteJpaRepository);
  }

}