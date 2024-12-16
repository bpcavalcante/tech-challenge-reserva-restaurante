package com.fiap.reservas_restaurantes.infraestructure.implementations;

import com.fiap.reservas_restaurantes.domain.ports.dto.RestauranteDatabaseDTO;
import com.fiap.reservas_restaurantes.infraestructure.RestauranteJpaRepository;
import com.fiap.reservas_restaurantes.infraestructure.entities.RestauranteEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RestauranteSqlRepositoryImplTest {
  @InjectMocks
  private RestauranteSqlRepositoryImpl restauranteSqlRepository;

  @Mock
  private RestauranteJpaRepository restauranteJpaRepository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testSaveRestauranteComSucesso() {
    // Arrange
    RestauranteDatabaseDTO restauranteDatabaseDTO =
        RestauranteDatabaseDTO.builder()
            .nome("Restaurante A")
            .localizacao("São Paulo")
            .tipoCozinha("Italiana")
            .capacidade(50)
            .horarioAbertura(LocalDateTime.of(2024, 12, 20, 19, 30))
            .horarioFechamento(LocalDateTime.of(2024, 12, 20, 19, 30))
            .build();

    RestauranteEntity restauranteEntity =
        RestauranteEntity.builder()
            .id(1L)
            .nome("Restaurante A")
            .localizacao("São Paulo")
            .tipoCozinha("Italiana")
            .capacidade(50)
            .horarioAbertura(LocalDateTime.of(2024, 12, 20, 19, 30))
            .horarioFechamento(LocalDateTime.of(2024, 12, 20, 19, 30))
            .build();

    when(restauranteJpaRepository.save(any(RestauranteEntity.class))).thenReturn(restauranteEntity);

    RestauranteDatabaseDTO result = restauranteSqlRepository.save(restauranteDatabaseDTO);

    assertEquals(1L, result.getId());
    assertEquals("Restaurante A", result.getNome());
    assertEquals("São Paulo", result.getLocalizacao());
    assertEquals("Italiana", result.getTipoCozinha());
    assertEquals(50, result.getCapacidade());
    assertEquals(LocalDateTime.of(2024, 12, 20, 19, 30), result.getHorarioAbertura());
    assertEquals(LocalDateTime.of(2024, 12, 20, 19, 30), result.getHorarioFechamento());

    verify(restauranteJpaRepository, times(1)).save(any(RestauranteEntity.class));
  }

  @Test
  void testSaveRestauranteErroAoSalvar() {
    RestauranteDatabaseDTO restauranteDatabaseDTO =
        RestauranteDatabaseDTO.builder()
            .nome("Restaurante A")
            .localizacao("São Paulo")
            .tipoCozinha("Italiana")
            .capacidade(50)
            .horarioAbertura(LocalDateTime.of(2024, 12, 20, 19, 30))
            .horarioFechamento(LocalDateTime.of(2024, 12, 20, 19, 30))
            .build();

    when(restauranteJpaRepository.save(any(RestauranteEntity.class)))
        .thenThrow(new DataAccessException("Erro ao salvar") {});

    RuntimeException exception =
        assertThrows(
            RuntimeException.class, () -> restauranteSqlRepository.save(restauranteDatabaseDTO));
    assertEquals("Erro ao tentar salvar o restaurante", exception.getMessage());

    verify(restauranteJpaRepository, times(1)).save(any(RestauranteEntity.class));
  }

  @Test
  void testBuscarRestaurantesComSucesso() {
    RestauranteEntity restauranteEntity1 =
        RestauranteEntity.builder()
            .id(1L)
            .nome("Restaurante A")
            .localizacao("São Paulo")
            .tipoCozinha("Italiana")
            .capacidade(50)
            .horarioAbertura(LocalDateTime.of(2024, 12, 20, 19, 30))
            .horarioFechamento(LocalDateTime.of(2024, 12, 20, 19, 30))
            .build();

    RestauranteEntity restauranteEntity2 =
        RestauranteEntity.builder()
            .id(2L)
            .nome("Restaurante B")
            .localizacao("Rio de Janeiro")
            .tipoCozinha("Japonesa")
            .capacidade(30)
            .horarioAbertura(LocalDateTime.of(2024, 12, 20, 19, 30))
            .horarioFechamento(LocalDateTime.of(2024, 12, 20, 19, 30))
            .build();

    when(restauranteJpaRepository.buscarComAvaliacoes(anyString(), anyString(), anyString()))
        .thenReturn(List.of(restauranteEntity1, restauranteEntity2));

    List<RestauranteDatabaseDTO> result =
        restauranteSqlRepository.buscar("Restaurante", "São Paulo", "Italiana");

    assertEquals(2, result.size());

    RestauranteDatabaseDTO restaurante1 = result.get(0);
    assertEquals("Restaurante A", restaurante1.getNome());
    assertEquals("São Paulo", restaurante1.getLocalizacao());
    assertEquals("Italiana", restaurante1.getTipoCozinha());

    RestauranteDatabaseDTO restaurante2 = result.get(1);
    assertEquals("Restaurante B", restaurante2.getNome());
    assertEquals("Rio de Janeiro", restaurante2.getLocalizacao());
    assertEquals("Japonesa", restaurante2.getTipoCozinha());

    verify(restauranteJpaRepository, times(1))
        .buscarComAvaliacoes(anyString(), anyString(), anyString());
  }
}
