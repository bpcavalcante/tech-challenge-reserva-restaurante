package com.fiap.reservas_restaurantes.domain.usecase;

import com.fiap.reservas_restaurantes.aplicacion.ports.CadastrarRestauranteUseCasePorts;
import com.fiap.reservas_restaurantes.aplicacion.ports.dto.RestauranteDTO;
import com.fiap.reservas_restaurantes.domain.Restaurante;
import com.fiap.reservas_restaurantes.domain.ports.RestauranteRepositoryPort;
import com.fiap.reservas_restaurantes.domain.ports.dto.RestauranteDatabaseDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CadastrarRestauranteUseCase implements CadastrarRestauranteUseCasePorts {

  private final RestauranteRepositoryPort restauranteRepositoryPort;

  @Override
  public RestauranteDTO cadastrar(RestauranteDTO restauranteDTO) {
    Restaurante restaurante = restauranteDTO.toDomain();
    RestauranteDatabaseDTO restauranteDatabaseDTO = restauranteRepositoryPort.save(restaurante.toDTO());
    return restauranteDatabaseDTO.toDTO();
  }
}
