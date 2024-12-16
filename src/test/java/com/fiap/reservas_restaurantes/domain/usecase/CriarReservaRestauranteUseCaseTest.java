package com.fiap.reservas_restaurantes.domain.usecase;

import com.fiap.reservas_restaurantes.aplicacion.ports.dto.ReservaDTO;
import com.fiap.reservas_restaurantes.domain.Reserva;
import com.fiap.reservas_restaurantes.domain.ports.ReservaRepositoryPort;
import com.fiap.reservas_restaurantes.domain.ports.dto.ReservaDatabaseDTO;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class CriarReservaRestauranteUseCaseTest {
  @InjectMocks
  private CriarReservaRestauranteUseCase criarReservaRestauranteUseCase;

  @Mock
  private ReservaRepositoryPort reservaRepositoryPort;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCriarReservaSemConflito() {
    ReservaDTO reservaDTO =
        ReservaDTO.builder()
            .nome("João Silva")
            .email("joao.silva@example.com")
            .quantidadePessoas(4)
            .dataHoraReserva(LocalDateTime.of(2024, 12, 20, 19, 30))
            .restauranteId(1L)
            .build();

    Reserva reserva =
        Reserva.builder()
            .nome("João Silva")
            .email("joao.silva@example.com")
            .quantidadePessoas(4)
            .dataHoraReserva(LocalDateTime.of(2024, 12, 20, 19, 30))
            .restauranteId(1L)
            .build();

    ReservaDatabaseDTO reservaDatabaseDTO =
        ReservaDatabaseDTO.builder()
            .id(1L)
            .nome("João Silva")
            .email("joao.silva@example.com")
            .quantidadePessoas(4)
            .dataHoraReserva(LocalDateTime.of(2024, 12, 20, 19, 30))
            .restauranteId(1L)
            .build();

    when(reservaRepositoryPort.existeConflito(eq(1L), any(LocalDateTime.class))).thenReturn(false);
    when(reservaRepositoryPort.save(any(ReservaDatabaseDTO.class))).thenReturn(reservaDatabaseDTO);

    ReservaDTO result = criarReservaRestauranteUseCase.criarReserva(reservaDTO);

    assertEquals(reservaDatabaseDTO.getId(), result.getId());
    assertEquals(reservaDatabaseDTO.getNome(), result.getNome());
    assertEquals(reservaDatabaseDTO.getEmail(), result.getEmail());
    assertEquals(reservaDatabaseDTO.getQuantidadePessoas(), result.getQuantidadePessoas());
    assertEquals(reservaDatabaseDTO.getDataHoraReserva(), result.getDataHoraReserva());
    assertEquals(reservaDatabaseDTO.getRestauranteId(), result.getRestauranteId());
  }

  @Test
  void testCriarReservaComConflito() {
    // Arrange
    ReservaDTO reservaDTO =
        ReservaDTO.builder()
            .nome("João Silva")
            .email("joao.silva@example.com")
            .quantidadePessoas(4)
            .dataHoraReserva(LocalDateTime.of(2024, 12, 20, 19, 30))
            .restauranteId(1L)
            .build();

    when(reservaRepositoryPort.existeConflito(eq(1L), any(LocalDateTime.class))).thenReturn(true);

    // Act & Assert
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> criarReservaRestauranteUseCase.criarReserva(reservaDTO));
    assertEquals("Já existe uma reserva para este horario", exception.getMessage());
  }
}
