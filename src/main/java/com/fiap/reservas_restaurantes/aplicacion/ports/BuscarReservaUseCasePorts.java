package com.fiap.reservas_restaurantes.aplicacion.ports;

import com.fiap.reservas_restaurantes.aplicacion.ports.dto.ReservaDTO;

public interface BuscarReservaUseCasePorts {
  ReservaDTO buscarReserva(Long id);
}
