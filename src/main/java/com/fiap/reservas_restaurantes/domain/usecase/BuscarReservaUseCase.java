package com.fiap.reservas_restaurantes.domain.usecase;

import com.fiap.reservas_restaurantes.aplicacion.ports.BuscarReservaUseCasePorts;
import com.fiap.reservas_restaurantes.aplicacion.ports.dto.ReservaDTO;
import com.fiap.reservas_restaurantes.domain.Reserva;
import com.fiap.reservas_restaurantes.domain.ports.ReservaRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuscarReservaUseCase implements BuscarReservaUseCasePorts {
  private final ReservaRepositoryPort reservaRepositoryPort;

  @Override
  public ReservaDTO buscarReserva(Long id) {
    Reserva reserva = reservaRepositoryPort.findById(id).orElseThrow(() -> new RuntimeException("Reserva não encontrada"));
    return reserva.toDTO();
  }
}
