package com.fiap.reservas_restaurantes.aplicacion.ports;

import com.fiap.reservas_restaurantes.aplicacion.ports.dto.ReservaDTO;

public interface AtualizarReservaUseCasePorts {
  ReservaDTO atualizarReserva(Long id, ReservaDTO reserva);
}
