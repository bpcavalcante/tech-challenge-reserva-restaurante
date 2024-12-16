package com.fiap.reservas_restaurantes.domain.usecase;

import com.fiap.reservas_restaurantes.aplicacion.controller.dto.output.RestauranteOutput;
import com.fiap.reservas_restaurantes.domain.ports.RestauranteRepositoryPort;
import com.fiap.reservas_restaurantes.domain.ports.dto.AvaliacaoDatabaseDTO;
import com.fiap.reservas_restaurantes.domain.ports.dto.RestauranteDatabaseDTO;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class BuscarRestauranteUseCaseTest {

  @InjectMocks
  private BuscarRestauranteUseCase buscarRestauranteUseCase;

  @Mock
  private RestauranteRepositoryPort restauranteRepository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testBuscarRestaurante() {
    // Arrange
    RestauranteDatabaseDTO restaurante1 = RestauranteDatabaseDTO.builder()
        .id(1L)
        .nome("Restaurante A")
        .localizacao("São Paulo")
        .tipoCozinha("Italiana")
        .capacidade(50)
        .horarioAbertura(LocalDateTime.of(2024, 12, 20, 11, 0))
        .horarioFechamento(LocalDateTime.of(2024, 12, 20, 23, 0))
        .avaliacoes(List.of(
            AvaliacaoDatabaseDTO.builder()
                .id(1L)
                .nota(4.5)
                .comentario("Ótimo ambiente!")
                .build(),
            AvaliacaoDatabaseDTO.builder()
                .id(2L)
                .nota(5.0)
                .comentario("Comida excelente!")
                .build()
        ))
        .build();

    RestauranteDatabaseDTO restaurante2 = RestauranteDatabaseDTO.builder()
        .id(2L)
        .nome("Restaurante B")
        .localizacao("Rio de Janeiro")
        .tipoCozinha("Japonesa")
        .capacidade(30)
        .horarioAbertura(LocalDateTime.of(2024, 12, 20, 12, 0))
        .horarioFechamento(LocalDateTime.of(2024, 12, 20, 22, 0))
        .avaliacoes(List.of(
            AvaliacaoDatabaseDTO.builder()
                .id(3L)
                .nota(4.0)
                .comentario("Boa experiência!")
                .build()
        ))
        .build();

    when(restauranteRepository.buscar(anyString(), anyString(), anyString()))
        .thenReturn(Arrays.asList(restaurante1, restaurante2));

    // Act
    List<RestauranteOutput> result = buscarRestauranteUseCase.buscar("Restaurante", "São Paulo", "Italiana");

    // Assert
    assertEquals(2, result.size());

    RestauranteOutput output1 = result.get(0);
    assertEquals("Restaurante A", output1.getNome());
    assertEquals("São Paulo", output1.getLocalizacao());
    assertEquals("Italiana", output1.getTipoCozinha());
    assertEquals(50, output1.getCapacidade());
    assertEquals(2, output1.getAvaliacoes().size());
    assertEquals(4.5, output1.getAvaliacoes().get(0).getNota());
    assertEquals("Ótimo ambiente!", output1.getAvaliacoes().get(0).getComentario());

    RestauranteOutput output2 = result.get(1);
    assertEquals("Restaurante B", output2.getNome());
    assertEquals("Rio de Janeiro", output2.getLocalizacao());
    assertEquals("Japonesa", output2.getTipoCozinha());
    assertEquals(30, output2.getCapacidade());
    assertEquals(1, output2.getAvaliacoes().size());
    assertEquals(4.0, output2.getAvaliacoes().get(0).getNota());
  }
}
