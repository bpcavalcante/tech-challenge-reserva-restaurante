package com.fiap.reservas_restaurantes.domain.ports;

import com.fiap.reservas_restaurantes.domain.ports.dto.RestauranteDatabaseDTO;

public interface RestauranteRepositoryPort {
  RestauranteDatabaseDTO save(RestauranteDatabaseDTO restauranteDatabaseDTO);
}
