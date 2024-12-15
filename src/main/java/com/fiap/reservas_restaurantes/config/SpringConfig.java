package com.fiap.reservas_restaurantes.config;

import com.fiap.reservas_restaurantes.domain.ports.AvaliacaoRepositoryPort;
import com.fiap.reservas_restaurantes.domain.ports.ReservaRepositoryPort;
import com.fiap.reservas_restaurantes.domain.ports.RestauranteRepositoryPort;
import com.fiap.reservas_restaurantes.domain.usecase.BuscarRestauranteUseCase;
import com.fiap.reservas_restaurantes.domain.usecase.CadastrarAvaliacaoUseCase;
import com.fiap.reservas_restaurantes.domain.usecase.CadastrarRestauranteUseCase;
import com.fiap.reservas_restaurantes.domain.usecase.CriarReservaRestauranteUseCase;
import com.fiap.reservas_restaurantes.infraestructure.AvaliacaoJpaRepository;
import com.fiap.reservas_restaurantes.infraestructure.ReservaJpaRepository;
import com.fiap.reservas_restaurantes.infraestructure.RestauranteJpaRepository;
import com.fiap.reservas_restaurantes.infraestructure.implementations.AvaliacaoSqlRepositoryImpl;
import com.fiap.reservas_restaurantes.infraestructure.implementations.ReservaSqlRepositoryImpl;
import com.fiap.reservas_restaurantes.infraestructure.implementations.RestauranteSqlRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringConfig implements WebMvcConfigurer {

  @Bean
  public CadastrarRestauranteUseCase cadastrarRestauranteUseCase(
      RestauranteRepositoryPort restauranteRepositoryPort) {
    return new CadastrarRestauranteUseCase(restauranteRepositoryPort);
  }

  @Bean
  public BuscarRestauranteUseCase buscarRestauranteUseCase(
      RestauranteRepositoryPort restauranteRepositoryPort) {
    return new BuscarRestauranteUseCase(restauranteRepositoryPort);
  }

  @Bean
  public CadastrarAvaliacaoUseCase cadastrarAvaliacaoUseCase(
      AvaliacaoRepositoryPort avaliacaoRepositoryPort) {
    return new CadastrarAvaliacaoUseCase(avaliacaoRepositoryPort);
  }

  @Bean
  public CriarReservaRestauranteUseCase criarReservaRestauranteUseCase(ReservaRepositoryPort reservaRepositoryPort) {
    return new CriarReservaRestauranteUseCase(reservaRepositoryPort);
  }

  @Bean
  public RestauranteSqlRepositoryImpl restauranteSqlRepositoryImpl(
      RestauranteJpaRepository restauranteJpaRepository) {
    return new RestauranteSqlRepositoryImpl(restauranteJpaRepository);
  }

  @Bean
  public AvaliacaoSqlRepositoryImpl avaliacaoSqlRepositoryImpl(
      AvaliacaoJpaRepository avaliacaoJpaRepository, RestauranteJpaRepository restauranteJpaRepository) {
    return new AvaliacaoSqlRepositoryImpl(avaliacaoJpaRepository, restauranteJpaRepository);
  }

  @Bean
  public ReservaSqlRepositoryImpl reservaSqlRepositoryImpl(ReservaJpaRepository reservaJpaRepository, RestauranteJpaRepository restauranteJpaRepository) {
    return new ReservaSqlRepositoryImpl(reservaJpaRepository, restauranteJpaRepository);
  }

}
