package com.fiap.reservas_restaurantes.domain.usecase;

import com.fiap.reservas_restaurantes.aplicacion.ports.dto.RestauranteDTO;
import com.fiap.reservas_restaurantes.domain.Restaurante;
import com.fiap.reservas_restaurantes.domain.ports.RestauranteRepositoryPort;
import com.fiap.reservas_restaurantes.domain.ports.dto.RestauranteDatabaseDTO;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CadastrarRestauranteUseCaseTest {
  @InjectMocks
  private CadastrarRestauranteUseCase cadastrarRestauranteUseCase;

  @Mock
  private RestauranteRepositoryPort restauranteRepositoryPort;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCadastrarRestaurante() {
    // Arrange
    RestauranteDTO restauranteDTO =
        RestauranteDTO.builder()
            .nome("Restaurante A")
            .localizacao("São Paulo")
            .tipoCozinha("Italiana")
            .capacidade(50)
            .horarioAbertura(LocalDateTime.of(2024, 12, 20, 19, 30))
            .horarioFechamento(LocalDateTime.of(2024, 12, 20, 19, 30))
            .build();

    Restaurante restaurante =
        Restaurante.builder()
            .nome("Restaurante A")
            .localizacao("São Paulo")
            .tipoCozinha("Italiana")
            .capacidade(50)
            .horarioAbertura(LocalDateTime.of(2024, 12, 20, 19, 30))
            .horarioFechamento(LocalDateTime.of(2024, 12, 20, 19, 30))
            .build();

    RestauranteDatabaseDTO restauranteDatabaseDTO =
        RestauranteDatabaseDTO.builder()
            .id(1L)
            .nome("Restaurante A")
            .localizacao("São Paulo")
            .tipoCozinha("Italiana")
            .capacidade(50)
            .horarioAbertura(LocalDateTime.of(2024, 12, 20, 19, 30))
            .horarioFechamento(LocalDateTime.of(2024, 12, 20, 19, 30))
            .build();

    when(restauranteRepositoryPort.save(any(RestauranteDatabaseDTO.class)))
        .thenReturn(restauranteDatabaseDTO);

    // Act
    RestauranteDTO result = cadastrarRestauranteUseCase.cadastrar(restauranteDTO);

    // Assert
    assertEquals(restauranteDatabaseDTO.getId(), result.getId());
    assertEquals(restauranteDatabaseDTO.getNome(), result.getNome());
    assertEquals(restauranteDatabaseDTO.getLocalizacao(), result.getLocalizacao());
    assertEquals(restauranteDatabaseDTO.getTipoCozinha(), result.getTipoCozinha());
    assertEquals(restauranteDatabaseDTO.getCapacidade(), result.getCapacidade());
    assertEquals(restauranteDatabaseDTO.getHorarioAbertura(), result.getHorarioAbertura());
    assertEquals(restauranteDatabaseDTO.getHorarioFechamento(), result.getHorarioFechamento());
  }
}
