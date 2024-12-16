package com.fiap.reservas_restaurantes.infraestructure.implementations;

import com.fiap.reservas_restaurantes.domain.Reserva;
import com.fiap.reservas_restaurantes.domain.ports.ReservaRepositoryPort;
import com.fiap.reservas_restaurantes.domain.ports.dto.ReservaDatabaseDTO;
import com.fiap.reservas_restaurantes.infraestructure.ReservaJpaRepository;
import com.fiap.reservas_restaurantes.infraestructure.RestauranteJpaRepository;
import com.fiap.reservas_restaurantes.infraestructure.entities.ReservaEntity;
import com.fiap.reservas_restaurantes.infraestructure.entities.RestauranteEntity;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;

@RequiredArgsConstructor
public class ReservaSqlRepositoryImpl implements ReservaRepositoryPort {

  private final ReservaJpaRepository reservaJpaRepository;
  private final RestauranteJpaRepository restauranteJpaRepository;

  @Override
  public ReservaDatabaseDTO save(ReservaDatabaseDTO reservaDatabaseDTO) {
    try {
      RestauranteEntity restaurante =
          restauranteJpaRepository
              .findById(reservaDatabaseDTO.getRestauranteId())
              .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

      ReservaEntity newReserva =
          ReservaEntity.builder()
              .id(reservaDatabaseDTO.getId())
              .nome(reservaDatabaseDTO.getNome())
              .email(reservaDatabaseDTO.getEmail())
              .quantidadePessoas(reservaDatabaseDTO.getQuantidadePessoas())
              .dataHoraReserva(reservaDatabaseDTO.getDataHoraReserva())
              .status(reservaDatabaseDTO.getStatus())
              .restaurante(restaurante)
              .build();

      return reservaJpaRepository.save(newReserva).toDatabaseDTO();

    } catch (DataAccessException e) {
      throw new RuntimeException("Erro ao inserir/atualizar reserva", e);
    }
  }

  @Override
  public boolean existeConflito(Long restauranteId, LocalDateTime dataHoraReserva) {
    return reservaJpaRepository.existsByRestauranteIdAndDataHoraReserva(
        restauranteId, dataHoraReserva);
  }

  @Override
  public Optional<Reserva> findById(Long id) {
    return reservaJpaRepository.findById(id).map(ReservaEntity::toDomain);
  }
}
