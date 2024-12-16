package com.fiap.reservas_restaurantes.domain.usecase;

import com.fiap.reservas_restaurantes.aplicacion.ports.dto.AvaliacaoDTO;
import com.fiap.reservas_restaurantes.domain.Avaliacao;
import com.fiap.reservas_restaurantes.domain.ports.AvaliacaoRepositoryPort;
import com.fiap.reservas_restaurantes.domain.ports.dto.AvaliacaoDatabaseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CadastrarAvaliacaoUseCaseTest {
  @InjectMocks
  private CadastrarAvaliacaoUseCase cadastrarAvaliacaoUseCase;

  @Mock
  private AvaliacaoRepositoryPort avaliacaoRepositoryPort;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCadastrarAvaliacao() {
    // Arrange
    AvaliacaoDTO avaliacaoDTO = AvaliacaoDTO.builder()
        .nota(4.5)
        .comentario("Excelente atendimento")
        .restauranteId(2L)
        .build();

    Avaliacao avaliacao = Avaliacao.builder()
        .nota(4.5)
        .comentario("Excelente atendimento")
        .restauranteId(2L)
        .build();

    AvaliacaoDatabaseDTO avaliacaoDatabaseDTO = AvaliacaoDatabaseDTO.builder()
        .id(10L)
        .nota(4.5)
        .comentario("Excelente atendimento")
        .restauranteId(2L)
        .build();

    when(avaliacaoRepositoryPort.save(any(AvaliacaoDatabaseDTO.class))).thenReturn(avaliacaoDatabaseDTO);

    // Act
    AvaliacaoDTO result = cadastrarAvaliacaoUseCase.cadastrar(avaliacaoDTO);

    // Assert
    assertEquals(avaliacaoDatabaseDTO.getId(), result.getId());
    assertEquals(avaliacaoDatabaseDTO.getNota(), result.getNota());
    assertEquals(avaliacaoDatabaseDTO.getComentario(), result.getComentario());
    assertEquals(avaliacaoDatabaseDTO.getRestauranteId(), result.getRestauranteId());
  }
}
