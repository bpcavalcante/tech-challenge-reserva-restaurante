package com.fiap.reservas_restaurantes.aplicacion.controller;

import com.fiap.reservas_restaurantes.aplicacion.controller.dto.input.ReservaInput;
import com.fiap.reservas_restaurantes.aplicacion.controller.dto.output.ReservaOutput;
import com.fiap.reservas_restaurantes.aplicacion.ports.AtualizarReservaUseCasePorts;
import com.fiap.reservas_restaurantes.aplicacion.ports.BuscarReservaUseCasePorts;
import com.fiap.reservas_restaurantes.aplicacion.ports.CriarReservaRestauranteUseCasePorts;
import com.fiap.reservas_restaurantes.aplicacion.ports.dto.ReservaDTO;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ReservaControllerTest {
  @InjectMocks private ReservaController reservaController;

  @Mock private CriarReservaRestauranteUseCasePorts criarReservaRestauranteUseCasePorts;

  @Mock private BuscarReservaUseCasePorts buscarReservaUseCasePorts;

  @Mock private AtualizarReservaUseCasePorts atualizarReservaUseCasePorts;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCriarReserva() {
    // Arrange
    ReservaInput reservaInput =
        ReservaInput.builder()
            .nome("João Silva")
            .email("joao.silva@example.com")
            .quantidadePessoas(4)
            .dataHoraReserva(LocalDateTime.of(2024, 12, 20, 19, 30))
            .restauranteId(1L)
            .build();

    ReservaDTO reservaDTO =
        ReservaDTO.builder()
            .nome("João Silva")
            .email("joao.silva@example.com")
            .quantidadePessoas(4)
            .dataHoraReserva(LocalDateTime.of(2024, 12, 20, 19, 30))
            .restauranteId(1L)
            .build();

    ReservaOutput reservaOutput = reservaDTO.toOutput();

    when(criarReservaRestauranteUseCasePorts.criarReserva(any(ReservaDTO.class)))
        .thenReturn(reservaDTO);

    // Act
    ResponseEntity<ReservaOutput> response = reservaController.criarReserva(reservaInput);

    // Assert
    assertEquals(201, response.getStatusCodeValue());
    assertEquals(reservaOutput, response.getBody());
  }

  @Test
  void testVisualizarReserva() {
    // Arrange
    Long id = 1L;

    ReservaDTO reservaDTO =
        ReservaDTO.builder()
            .nome("João Silva")
            .email("joao.silva@example.com")
            .quantidadePessoas(4)
            .dataHoraReserva(LocalDateTime.of(2024, 12, 20, 19, 30))
            .restauranteId(1L)
            .build();

    ReservaOutput reservaOutput = reservaDTO.toOutput();

    when(buscarReservaUseCasePorts.buscarReserva(id)).thenReturn(reservaDTO);

    // Act
    ResponseEntity<ReservaOutput> response = reservaController.visualizarReserva(id);

    // Assert
    assertEquals(200, response.getStatusCodeValue());
    assertEquals(reservaOutput, response.getBody());
  }

  @Test
  void testAtualizarReserva() {
    // Arrange
    Long id = 1L;

    ReservaInput reservaInput =
        ReservaInput.builder()
            .nome("João Silva Atualizado")
            .email("joao.silva.atualizado@example.com")
            .quantidadePessoas(5)
            .dataHoraReserva(LocalDateTime.of(2024, 12, 25, 20, 0))
            .restauranteId(2L)
            .build();

    ReservaDTO reservaDTO =
        ReservaDTO.builder()
            .nome("João Silva Atualizado")
            .email("joao.silva.atualizado@example.com")
            .quantidadePessoas(5)
            .dataHoraReserva(LocalDateTime.of(2024, 12, 25, 20, 0))
            .restauranteId(2L)
            .build();

    ReservaOutput reservaOutput = reservaDTO.toOutput();

    when(atualizarReservaUseCasePorts.atualizarReserva(any(Long.class), any(ReservaDTO.class)))
        .thenReturn(reservaDTO);

    // Act
    ResponseEntity<ReservaOutput> response = reservaController.atualizarReserva(id, reservaInput);

    // Assert
    assertEquals(200, response.getStatusCodeValue());
    assertEquals(reservaOutput, response.getBody());
  }
}
