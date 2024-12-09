package com.fiap.reservas_restaurantes.aplicacion.ports;

import com.fiap.reservas_restaurantes.aplicacion.ports.dto.RestauranteDTO;

public interface CadastrarRestauranteUseCasePorts {
  RestauranteDTO cadastrar(RestauranteDTO restauranteDTO);
}
