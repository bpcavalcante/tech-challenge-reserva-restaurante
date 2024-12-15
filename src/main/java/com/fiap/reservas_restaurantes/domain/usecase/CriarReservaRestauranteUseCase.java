package com.fiap.reservas_restaurantes.domain.usecase;

import com.fiap.reservas_restaurantes.aplicacion.ports.CriarReservaRestauranteUseCasePorts;
import com.fiap.reservas_restaurantes.aplicacion.ports.dto.ReservaDTO;
import com.fiap.reservas_restaurantes.domain.Reserva;
import com.fiap.reservas_restaurantes.domain.ports.ReservaRepositoryPort;
import com.fiap.reservas_restaurantes.domain.ports.dto.ReservaDatabaseDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CriarReservaRestauranteUseCase implements CriarReservaRestauranteUseCasePorts {

  private final ReservaRepositoryPort reservaRepositoryPort;

  @Override
  public ReservaDTO criarReserva(ReservaDTO reservaDTO) {
    if (reservaRepositoryPort.existeConflito(
        reservaDTO.getRestauranteId(), reservaDTO.getDataHoraReserva())) {
      throw new IllegalArgumentException("Já existe uma reserva para este horario");
    }
    Reserva reserva = reservaDTO.toDomain();
    ReservaDatabaseDTO reservaDatabaseDTO = reservaRepositoryPort.save(reserva.toDTO());
    return reservaDatabaseDTO.toDTO();
  }
}
