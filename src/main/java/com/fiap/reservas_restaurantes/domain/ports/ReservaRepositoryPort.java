package com.fiap.reservas_restaurantes.domain.ports;

import com.fiap.reservas_restaurantes.domain.Reserva;
import com.fiap.reservas_restaurantes.domain.ports.dto.ReservaDatabaseDTO;
import java.time.LocalDateTime;
import java.util.Optional;

public interface ReservaRepositoryPort {
  ReservaDatabaseDTO save(ReservaDatabaseDTO reservaDatabseDTO);
  boolean existeConflito(Long restauranteId , LocalDateTime dataHoraReserva);
  Optional<Reserva> findById(Long id);
}
