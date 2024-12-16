package com.fiap.reservas_restaurantes.infraestructure.implementations;

import com.fiap.reservas_restaurantes.domain.Reserva;
import com.fiap.reservas_restaurantes.domain.ports.dto.ReservaDatabaseDTO;
import com.fiap.reservas_restaurantes.infraestructure.ReservaJpaRepository;
import com.fiap.reservas_restaurantes.infraestructure.RestauranteJpaRepository;
import com.fiap.reservas_restaurantes.infraestructure.entities.ReservaEntity;
import com.fiap.reservas_restaurantes.infraestructure.entities.RestauranteEntity;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ReservaSqlRepositoryImplTest {
  @InjectMocks
  private ReservaSqlRepositoryImpl reservaSqlRepository;

  @Mock
  private ReservaJpaRepository reservaJpaRepository;

  @Mock
  private RestauranteJpaRepository restauranteJpaRepository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testSaveReservaComSucesso() {
    // Arrange
    ReservaDatabaseDTO reservaDatabaseDTO = ReservaDatabaseDTO.builder()
        .id(1L)
        .nome("João Silva")
        .email("joao.silva@example.com")
        .quantidadePessoas(4)
        .dataHoraReserva(LocalDateTime.of(2024, 12, 20, 19, 30))
        .status("CONFIRMADA")
        .restauranteId(1L)
        .build();

    RestauranteEntity restauranteEntity = RestauranteEntity.builder()
        .id(1L)
        .nome("Restaurante Teste")
        .build();

    ReservaEntity reservaEntity = ReservaEntity.builder()
        .id(1L)
        .nome("João Silva")
        .email("joao.silva@example.com")
        .quantidadePessoas(4)
        .dataHoraReserva(LocalDateTime.of(2024, 12, 20, 19, 30))
        .status("CONFIRMADA")
        .restaurante(restauranteEntity)
        .build();

    when(restauranteJpaRepository.findById(1L)).thenReturn(Optional.of(restauranteEntity));
    when(reservaJpaRepository.save(any(ReservaEntity.class))).thenReturn(reservaEntity);

    // Act
    ReservaDatabaseDTO result = reservaSqlRepository.save(reservaDatabaseDTO);

    // Assert
    assertEquals(1L, result.getId());
    assertEquals("João Silva", result.getNome());
    assertEquals("joao.silva@example.com", result.getEmail());
    assertEquals(4, result.getQuantidadePessoas());
    assertEquals(LocalDateTime.of(2024, 12, 20, 19, 30), result.getDataHoraReserva());
    assertEquals("CONFIRMADA", result.getStatus());
    assertEquals(1L, result.getRestauranteId());

    verify(restauranteJpaRepository, times(1)).findById(1L);
    verify(reservaJpaRepository, times(1)).save(any(ReservaEntity.class));
  }

  @Test
  void testSaveReservaRestauranteNaoEncontrado() {
    // Arrange
    ReservaDatabaseDTO reservaDatabaseDTO = ReservaDatabaseDTO.builder()
        .restauranteId(1L)
        .build();

    when(restauranteJpaRepository.findById(1L)).thenReturn(Optional.empty());

    // Act & Assert
    RuntimeException exception = assertThrows(RuntimeException.class, () ->
        reservaSqlRepository.save(reservaDatabaseDTO));
    assertEquals("Restaurante não encontrado", exception.getMessage());

    verify(restauranteJpaRepository, times(1)).findById(1L);
    verify(reservaJpaRepository, never()).save(any(ReservaEntity.class));
  }

  @Test
  void testExisteConflito() {
    // Arrange
    Long restauranteId = 1L;
    LocalDateTime dataHoraReserva = LocalDateTime.of(2024, 12, 20, 19, 30);

    when(reservaJpaRepository.existsByRestauranteIdAndDataHoraReserva(restauranteId, dataHoraReserva))
        .thenReturn(true);

    // Act
    boolean conflito = reservaSqlRepository.existeConflito(restauranteId, dataHoraReserva);

    // Assert
    assertTrue(conflito);

    verify(reservaJpaRepository, times(1))
        .existsByRestauranteIdAndDataHoraReserva(restauranteId, dataHoraReserva);
  }

  @Test
  void testFindById() {
    // Arrange
    Long id = 1L;

    RestauranteEntity restauranteEntity = RestauranteEntity.builder()
        .id(1L)
        .nome("Restaurante Teste")
        .build();

    ReservaEntity reservaEntity = ReservaEntity.builder()
        .id(1L)
        .nome("João Silva")
        .email("joao.silva@example.com")
        .quantidadePessoas(4)
        .dataHoraReserva(LocalDateTime.of(2024, 12, 20, 19, 30))
        .status("CONFIRMADA")
        .restaurante(restauranteEntity)
        .build();

    when(reservaJpaRepository.findById(1L)).thenReturn(Optional.of(reservaEntity));

    // Act
    Optional<Reserva> result = reservaSqlRepository.findById(id);

    // Assert
    assertTrue(result.isPresent());
    assertEquals("João Silva", result.get().getNome());
    assertEquals("CONFIRMADA", result.get().getStatus());

    verify(reservaJpaRepository, times(1)).findById(1L);
  }
}
