package com.fiap.reservas_restaurantes.domain.usecase;

import com.fiap.reservas_restaurantes.aplicacion.ports.dto.ReservaDTO;
import com.fiap.reservas_restaurantes.domain.Reserva;
import com.fiap.reservas_restaurantes.domain.ports.ReservaRepositoryPort;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class BuscarReservaUseCaseTest {

  @InjectMocks
  private BuscarReservaUseCase buscarReservaUseCase;

  @Mock
  private ReservaRepositoryPort reservaRepositoryPort;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testBuscarReserva() {
    Long id = 1L;

    Reserva reserva = new Reserva();
    reserva.setId(id);
    reserva.setNome("João Silva");
    reserva.setEmail("joao.silva@example.com");
    reserva.setQuantidadePessoas(4);
    reserva.setDataHoraReserva(LocalDateTime.of(2024, 12, 20, 19, 30));
    reserva.setStatus("CONFIRMADA");

    ReservaDTO reservaDTO = reserva.toDTO();

    when(reservaRepositoryPort.findById(eq(id))).thenReturn(Optional.of(reserva));

    ReservaDTO result = buscarReservaUseCase.buscarReserva(id);

    assertEquals(reservaDTO.getNome(), result.getNome());
    assertEquals(reservaDTO.getEmail(), result.getEmail());
    assertEquals(reservaDTO.getQuantidadePessoas(), result.getQuantidadePessoas());
    assertEquals(reservaDTO.getDataHoraReserva(), result.getDataHoraReserva());
    assertEquals(reservaDTO.getStatus(), result.getStatus());
  }

  @Test
  void testBuscarReservaNotFound() {
    Long id = 1L;

    when(reservaRepositoryPort.findById(eq(id))).thenReturn(Optional.empty());

    assertThrows(RuntimeException.class, () -> buscarReservaUseCase.buscarReserva(id));
  }
}
