package com.fiap.reservas_restaurantes.aplicacion.controller;

import com.fiap.reservas_restaurantes.aplicacion.controller.dto.input.AvaliacaoInput;
import com.fiap.reservas_restaurantes.aplicacion.controller.dto.output.AvaliacaoOutput;
import com.fiap.reservas_restaurantes.aplicacion.ports.CadastrarAvaliacaoUseCasePorts;
import com.fiap.reservas_restaurantes.aplicacion.ports.dto.AvaliacaoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AvaliacaoControllerTest {
  @InjectMocks private AvaliacaoController avaliacaoController;

  @Mock private CadastrarAvaliacaoUseCasePorts cadastrarAvaliacaoUseCasePorts;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCadastrarAvaliacao() {
    // Arrange
    AvaliacaoInput avaliacaoInput =
        AvaliacaoInput.builder()
            .nota(5.0)
            .comentario("Excelente serviço!")
            .restauranteId(2L)
            .build();

    AvaliacaoDTO avaliacaoDTO =
        AvaliacaoDTO.builder()
            .nota(5.0)
            .comentario("Excelente serviço!")
            .id(1L)
            .restauranteId(2L)
            .build();

    AvaliacaoOutput avaliacaoOutput = avaliacaoDTO.toOutput();

    when(cadastrarAvaliacaoUseCasePorts.cadastrar(any(AvaliacaoDTO.class)))
        .thenReturn(avaliacaoDTO);

    ResponseEntity<AvaliacaoOutput> response = avaliacaoController.cadastrar(avaliacaoInput);

    assertEquals(201, response.getStatusCodeValue());
    assertEquals(avaliacaoOutput, response.getBody());
  }
}
