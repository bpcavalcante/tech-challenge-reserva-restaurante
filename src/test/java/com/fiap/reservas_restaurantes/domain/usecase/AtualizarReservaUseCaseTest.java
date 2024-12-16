package com.fiap.reservas_restaurantes.domain.usecase;

import com.fiap.reservas_restaurantes.aplicacion.ports.dto.ReservaDTO;
import com.fiap.reservas_restaurantes.domain.Reserva;
import com.fiap.reservas_restaurantes.domain.ports.ReservaRepositoryPort;
import com.fiap.reservas_restaurantes.domain.ports.dto.ReservaDatabaseDTO;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class AtualizarReservaUseCaseTest {
  @InjectMocks
  private AtualizarReservaUseCase atualizarReservaUseCase;

  @Mock
  private ReservaRepositoryPort reservaRepositoryPort;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testAtualizarReserva() {
    Long id = 1L;

    Reserva reservaExistente = new Reserva();
    reservaExistente.setId(id);
    reservaExistente.setNome("Nome Antigo");
    reservaExistente.setEmail("email.antigo@example.com");
    reservaExistente.setQuantidadePessoas(3);
    reservaExistente.setDataHoraReserva(LocalDateTime.of(2024, 12, 20, 19, 30));
    reservaExistente.setStatus("CONFIRMADA");

    ReservaDTO reservaDTO = ReservaDTO.builder()
        .nome("Nome Atualizado")
        .email("email.atualizado@example.com")
        .quantidadePessoas(4)
        .dataHoraReserva(LocalDateTime.of(2024, 12, 25, 20, 0))
        .status("ATUALIZADA")
        .build();

    ReservaDatabaseDTO reservaDatabaseDTO = ReservaDatabaseDTO.builder()
        .id(id)
        .nome("Nome Atualizado")
        .email("email.atualizado@example.com")
        .quantidadePessoas(4)
        .dataHoraReserva(LocalDateTime.of(2024, 12, 25, 20, 0))
        .status("ATUALIZADA")
        .build();

    when(reservaRepositoryPort.findById(eq(id))).thenReturn(Optional.of(reservaExistente));
    when(reservaRepositoryPort.save(any(ReservaDatabaseDTO.class))).thenReturn(reservaDatabaseDTO);

    ReservaDTO updatedReservaDTO = atualizarReservaUseCase.atualizarReserva(id, reservaDTO);

    assertEquals(reservaDTO.getNome(), updatedReservaDTO.getNome());
    assertEquals(reservaDTO.getEmail(), updatedReservaDTO.getEmail());
    assertEquals(reservaDTO.getQuantidadePessoas(), updatedReservaDTO.getQuantidadePessoas());
    assertEquals(reservaDTO.getDataHoraReserva(), updatedReservaDTO.getDataHoraReserva());
    assertEquals(reservaDTO.getStatus(), updatedReservaDTO.getStatus());
  }

  @Test
  void testAtualizarReservaNotFound() {
    Long id = 1L;
    ReservaDTO reservaDTO = ReservaDTO.builder()
        .nome("Nome Atualizado")
        .email("email.atualizado@example.com")
        .quantidadePessoas(4)
        .dataHoraReserva(LocalDateTime.of(2024, 12, 25, 20, 0))
        .status("ATUALIZADA")
        .build();

    when(reservaRepositoryPort.findById(eq(id))).thenReturn(Optional.empty());

    assertThrows(RuntimeException.class, () -> atualizarReservaUseCase.atualizarReserva(id, reservaDTO));
  }
}
