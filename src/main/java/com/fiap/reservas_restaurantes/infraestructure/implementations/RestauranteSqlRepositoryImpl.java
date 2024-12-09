package com.fiap.reservas_restaurantes.infraestructure.implementations;

import com.fiap.reservas_restaurantes.domain.ports.RestauranteRepositoryPort;
import com.fiap.reservas_restaurantes.domain.ports.dto.RestauranteDatabaseDTO;
import com.fiap.reservas_restaurantes.infraestructure.RestauranteJpaRepository;
import com.fiap.reservas_restaurantes.infraestructure.entities.RestauranteEntity;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;

@RequiredArgsConstructor
public class RestauranteSqlRepositoryImpl implements RestauranteRepositoryPort {
  private final RestauranteJpaRepository restauranteJpaRepository;

  @Override
  @Transactional
  public RestauranteDatabaseDTO save(RestauranteDatabaseDTO restauranteDatabaseDTO) {
    RestauranteEntity newRestaurante =
        RestauranteEntity.builder()
            .nome(restauranteDatabaseDTO.getNome())
            .localizacao(restauranteDatabaseDTO.getLocalizacao())
            .tipoCozinha(restauranteDatabaseDTO.getTipoCozinha())
            .capacidade(restauranteDatabaseDTO.getCapacidade())
            .horarioAbertura(restauranteDatabaseDTO.getHorarioAbertura())
            .horarioFechamento(restauranteDatabaseDTO.getHorarioFechamento())
            .build();

    try {
      return restauranteJpaRepository.save(newRestaurante).toDatabaseDTO();
    } catch (DataAccessException e) {
      throw new RuntimeException("Erro ao tentar salvar o restaurante", e);
    }
  }

  @Override
  public List<RestauranteDatabaseDTO> buscar(String nome, String localizacao, String tipoCozinha) {
    return restauranteJpaRepository.buscar(nome, localizacao, tipoCozinha).stream()
        .map(RestauranteEntity::toDatabaseDTO)
        .collect(Collectors.toList());
  }
}
