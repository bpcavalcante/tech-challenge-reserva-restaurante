package com.fiap.reservas_restaurantes.domain.ports;

import com.fiap.reservas_restaurantes.domain.ports.dto.RestauranteDatabaseDTO;
import java.util.List;

public interface RestauranteRepositoryPort {
  RestauranteDatabaseDTO save(RestauranteDatabaseDTO restauranteDatabaseDTO);
  List<RestauranteDatabaseDTO> buscar(String nome, String localizacao, String tipoCozinha);
}
