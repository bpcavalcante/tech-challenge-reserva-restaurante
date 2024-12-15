package com.fiap.reservas_restaurantes.aplicacion.ports;

import com.fiap.reservas_restaurantes.aplicacion.ports.dto.ReservaDTO;

public interface CriarReservaRestauranteUseCasePorts {
  ReservaDTO criarReserva(ReservaDTO reservaDTO);
}
