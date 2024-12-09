package com.fiap.reservas_restaurantes.aplicacion.controller;

import com.fiap.reservas_restaurantes.aplicacion.controller.dto.input.RestauranteInput;
import com.fiap.reservas_restaurantes.aplicacion.controller.dto.output.RestauranteOutput;
import com.fiap.reservas_restaurantes.aplicacion.ports.BuscarRestauranteUseCasePorts;
import com.fiap.reservas_restaurantes.aplicacion.ports.CadastrarRestauranteUseCasePorts;
import com.fiap.reservas_restaurantes.aplicacion.ports.dto.RestauranteDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/restaurantes")
@RequiredArgsConstructor
public class RestauranteController {

  private final CadastrarRestauranteUseCasePorts cadastrarRestauranteUseCasePorts;
  private final BuscarRestauranteUseCasePorts buscarRestauranteUseCasePorts;

  @PostMapping
  public ResponseEntity<RestauranteOutput> cadastrarRestaurante(
      @RequestBody RestauranteInput restauranteInput) {
    RestauranteDTO restauranteDTO =
        cadastrarRestauranteUseCasePorts.cadastrar(restauranteInput.restauranteInputToDTO());
    return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(restauranteDTO.toOutput());
  }

  @GetMapping
  public ResponseEntity<List<RestauranteOutput>> buscarRestaurantes(
      @RequestParam(required = false) String nome,
      @RequestParam(required = false) String localizacao,
      @RequestParam(required = false) String tipoCozinha) {
    List<RestauranteOutput> restaurantes =
        buscarRestauranteUseCasePorts.buscar(nome, localizacao, tipoCozinha);
    return ResponseEntity.ok(restaurantes);
  }
}
