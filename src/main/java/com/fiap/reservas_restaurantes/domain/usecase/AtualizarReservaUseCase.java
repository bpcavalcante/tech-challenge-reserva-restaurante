package com.fiap.reservas_restaurantes.domain.usecase;

import com.fiap.reservas_restaurantes.aplicacion.ports.AtualizarReservaUseCasePorts;
import com.fiap.reservas_restaurantes.aplicacion.ports.dto.ReservaDTO;
import com.fiap.reservas_restaurantes.domain.Reserva;
import com.fiap.reservas_restaurantes.domain.ports.ReservaRepositoryPort;
import com.fiap.reservas_restaurantes.domain.ports.dto.ReservaDatabaseDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AtualizarReservaUseCase implements AtualizarReservaUseCasePorts {

  private final ReservaRepositoryPort reservaRepositoryPort;

  @Override
  public ReservaDTO atualizarReserva(Long id, ReservaDTO reserva) {
    Reserva reservaExistente =
        reservaRepositoryPort
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

    reservaExistente.setNome(reserva.getNome());
    reservaExistente.setEmail(reserva.getEmail());
    reservaExistente.setQuantidadePessoas(reserva.getQuantidadePessoas());
    reservaExistente.setDataHoraReserva(reserva.getDataHoraReserva());
    reservaExistente.setStatus(reserva.getStatus());
    ReservaDatabaseDTO updatedReservaDatabaseDTO =
        reservaRepositoryPort.save(reservaExistente.toDatabaseDTO());
    return updatedReservaDatabaseDTO.toDTO();
  }
}
