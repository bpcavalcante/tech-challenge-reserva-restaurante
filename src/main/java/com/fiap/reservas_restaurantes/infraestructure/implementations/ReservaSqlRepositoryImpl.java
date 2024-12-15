package com.fiap.reservas_restaurantes.infraestructure.implementations;

import com.fiap.reservas_restaurantes.domain.ports.ReservaRepositoryPort;
import com.fiap.reservas_restaurantes.domain.ports.dto.ReservaDatabaseDTO;
import com.fiap.reservas_restaurantes.infraestructure.ReservaJpaRepository;
import com.fiap.reservas_restaurantes.infraestructure.RestauranteJpaRepository;
import com.fiap.reservas_restaurantes.infraestructure.entities.ReservaEntity;
import com.fiap.reservas_restaurantes.infraestructure.entities.RestauranteEntity;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;

@RequiredArgsConstructor
public class ReservaSqlRepositoryImpl implements ReservaRepositoryPort {

  private final ReservaJpaRepository reservaJpaRepository;
  private final RestauranteJpaRepository restauranteJpaRepository;

  @Override
  public ReservaDatabaseDTO save(ReservaDatabaseDTO reservaDatabaseDTO) {
    try{
      RestauranteEntity restaurante =
          restauranteJpaRepository.findById(reservaDatabaseDTO.getRestauranteId())
              .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

      ReservaEntity newReserva = ReservaEntity.builder().nome(reservaDatabaseDTO.getNome())
          .email(reservaDatabaseDTO.getEmail())
          .quantidadePessoas(reservaDatabaseDTO.getQuantidadePessoas())
          .dataHoraReserva(reservaDatabaseDTO.getDataHoraReserva())
          .restaurante(restaurante).build();

      return reservaJpaRepository.save(newReserva).toDatabaseDTO();

    } catch (DataAccessException e) {
      throw new RuntimeException("Erro ao inserir reserva", e);
    }
  }

  @Override
  public boolean existeConflito(Long restauranteId, LocalDateTime dataHoraReserva) {
    return reservaJpaRepository.existsByRestauranteIdAndDataHoraReserva(restauranteId, dataHoraReserva);
  }
}