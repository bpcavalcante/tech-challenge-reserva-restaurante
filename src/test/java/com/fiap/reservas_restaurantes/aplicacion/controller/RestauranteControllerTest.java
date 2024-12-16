package com.fiap.reservas_restaurantes.aplicacion.controller;

import com.fiap.reservas_restaurantes.aplicacion.controller.dto.input.RestauranteInput;
import com.fiap.reservas_restaurantes.aplicacion.controller.dto.output.RestauranteOutput;
import com.fiap.reservas_restaurantes.aplicacion.ports.BuscarRestauranteUseCasePorts;
import com.fiap.reservas_restaurantes.aplicacion.ports.CadastrarRestauranteUseCasePorts;
import com.fiap.reservas_restaurantes.aplicacion.ports.dto.RestauranteDTO;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class RestauranteControllerTest {

  @InjectMocks private RestauranteController restauranteController;

  @Mock private CadastrarRestauranteUseCasePorts cadastrarRestauranteUseCasePorts;

  @Mock private BuscarRestauranteUseCasePorts buscarRestauranteUseCasePorts;

  public RestauranteControllerTest() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCadastrarRestaurante() {
    RestauranteInput restauranteInput =
        RestauranteInput.builder()
            .nome("Restaurante Teste")
            .localizacao("São Paulo")
            .tipoCozinha("Brasileira")
            .capacidade(50)
            .horarioAbertura(LocalDateTime.of(2024, 12, 20, 9, 0))
            .horarioFechamento(LocalDateTime.of(2024, 12, 20, 22, 0))
            .build();

    RestauranteDTO restauranteDTO =
        RestauranteDTO.builder()
            .nome("Restaurante Teste")
            .localizacao("São Paulo")
            .tipoCozinha("Brasileira")
            .capacidade(50)
            .horarioAbertura(LocalDateTime.of(2024, 12, 20, 9, 0))
            .horarioFechamento(LocalDateTime.of(2024, 12, 20, 22, 0))
            .build();

    RestauranteOutput restauranteOutput = restauranteDTO.toOutput();

    when(cadastrarRestauranteUseCasePorts.cadastrar(any(RestauranteDTO.class)))
        .thenReturn(restauranteDTO);

    ResponseEntity<RestauranteOutput> response =
        restauranteController.cadastrarRestaurante(restauranteInput);

    assertEquals(201, response.getStatusCodeValue());
    assertEquals(restauranteOutput, response.getBody());
  }

  @Test
  void testBuscarRestaurantes() {
    RestauranteOutput restaurante1 = new RestauranteOutput();
    restaurante1.setNome("Restaurante 1");
    restaurante1.setLocalizacao("São Paulo");
    restaurante1.setTipoCozinha("Italiana");

    RestauranteOutput restaurante2 = new RestauranteOutput();
    restaurante2.setNome("Restaurante 2");
    restaurante2.setLocalizacao("Rio de Janeiro");
    restaurante2.setTipoCozinha("Japonesa");

    List<RestauranteOutput> restaurantes = List.of(restaurante1, restaurante2);

    when(buscarRestauranteUseCasePorts.buscar(null, null, null)).thenReturn(restaurantes);

    // Act
    ResponseEntity<List<RestauranteOutput>> response =
        restauranteController.buscarRestaurantes(null, null, null);

    // Assert
    assertEquals(200, response.getStatusCodeValue());
    assertEquals(restaurantes, response.getBody());
  }
}
