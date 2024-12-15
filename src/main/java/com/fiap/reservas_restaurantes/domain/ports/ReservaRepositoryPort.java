package com.fiap.reservas_restaurantes.domain.ports;

import com.fiap.reservas_restaurantes.domain.ports.dto.ReservaDatabaseDTO;
import java.time.LocalDateTime;

public interface ReservaRepositoryPort {
  ReservaDatabaseDTO save(ReservaDatabaseDTO reservaDatabseDTO);
  boolean existeConflito(Long restauranteId , LocalDateTime dataHoraReserva);
}
