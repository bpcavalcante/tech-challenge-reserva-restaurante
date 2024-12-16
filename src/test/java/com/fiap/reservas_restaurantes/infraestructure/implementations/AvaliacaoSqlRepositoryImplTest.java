package com.fiap.reservas_restaurantes.infraestructure.implementations;

import com.fiap.reservas_restaurantes.domain.ports.dto.AvaliacaoDatabaseDTO;
import com.fiap.reservas_restaurantes.infraestructure.AvaliacaoJpaRepository;
import com.fiap.reservas_restaurantes.infraestructure.RestauranteJpaRepository;
import com.fiap.reservas_restaurantes.infraestructure.entities.AvaliacaoEntity;
import com.fiap.reservas_restaurantes.infraestructure.entities.RestauranteEntity;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AvaliacaoSqlRepositoryImplTest {
  @InjectMocks
  private AvaliacaoSqlRepositoryImpl avaliacaoSqlRepository;

  @Mock
  private AvaliacaoJpaRepository avaliacaoJpaRepository;

  @Mock private RestauranteJpaRepository restauranteJpaRepository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testSaveAvaliacaoComSucesso() {
    AvaliacaoDatabaseDTO avaliacaoDatabaseDTO =
        AvaliacaoDatabaseDTO.builder()
            .nota(4.5)
            .comentario("Ótima comida!")
            .restauranteId(1L)
            .build();

    RestauranteEntity restauranteEntity =
        RestauranteEntity.builder().id(1L).nome("Restaurante Teste").build();

    AvaliacaoEntity avaliacaoEntity =
        AvaliacaoEntity.builder()
            .id(10L)
            .nota(4.5)
            .comentario("Ótima comida!")
            .restaurante(restauranteEntity)
            .build();

    when(restauranteJpaRepository.findById(1L)).thenReturn(Optional.of(restauranteEntity));
    when(avaliacaoJpaRepository.save(any(AvaliacaoEntity.class))).thenReturn(avaliacaoEntity);

    AvaliacaoDatabaseDTO result = avaliacaoSqlRepository.save(avaliacaoDatabaseDTO);

    assertEquals(10L, result.getId());
    assertEquals(4.5, result.getNota());
    assertEquals("Ótima comida!", result.getComentario());
    assertEquals(1L, result.getRestauranteId());

    verify(restauranteJpaRepository, times(1)).findById(1L);
    verify(avaliacaoJpaRepository, times(1)).save(any(AvaliacaoEntity.class));
  }

  @Test
  void testSaveAvaliacaoRestauranteNaoEncontrado() {
    AvaliacaoDatabaseDTO avaliacaoDatabaseDTO =
        AvaliacaoDatabaseDTO.builder()
            .nota(4.5)
            .comentario("Ótima comida!")
            .restauranteId(1L)
            .build();

    when(restauranteJpaRepository.findById(1L)).thenReturn(Optional.empty());

    RuntimeException exception =
        assertThrows(
            RuntimeException.class, () -> avaliacaoSqlRepository.save(avaliacaoDatabaseDTO));
    assertEquals("Restaurante não encontrado", exception.getMessage());

    verify(restauranteJpaRepository, times(1)).findById(1L);
    verify(avaliacaoJpaRepository, never()).save(any(AvaliacaoEntity.class));
  }

  @Test
  void testSaveAvaliacaoErroAoInserir() {
    AvaliacaoDatabaseDTO avaliacaoDatabaseDTO =
        AvaliacaoDatabaseDTO.builder()
            .nota(4.5)
            .comentario("Ótima comida!")
            .restauranteId(1L)
            .build();

    RestauranteEntity restauranteEntity =
        RestauranteEntity.builder().id(1L).nome("Restaurante Teste").build();

    when(restauranteJpaRepository.findById(1L)).thenReturn(Optional.of(restauranteEntity));
    when(avaliacaoJpaRepository.save(any(AvaliacaoEntity.class)))
        .thenThrow(new DataAccessException("Erro no banco") {});

    RuntimeException exception =
        assertThrows(
            RuntimeException.class, () -> avaliacaoSqlRepository.save(avaliacaoDatabaseDTO));
    assertEquals("Erro ao inserir avaliacao", exception.getMessage());

    verify(restauranteJpaRepository, times(1)).findById(1L);
    verify(avaliacaoJpaRepository, times(1)).save(any(AvaliacaoEntity.class));
  }
}
