package com.fiap.reservas_restaurantes.infraestructure;

import com.fiap.reservas_restaurantes.infraestructure.entities.ReservaEntity;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaJpaRepository extends JpaRepository<ReservaEntity, Long> {
  boolean existsByRestauranteIdAndDataHoraReserva(Long restauranteId, LocalDateTime dataHoraReserva);
}
