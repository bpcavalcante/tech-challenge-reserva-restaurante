package com.fiap.reservas_restaurantes.aplicacion.controller;

import com.fiap.reservas_restaurantes.aplicacion.controller.dto.input.ReservaInput;
import com.fiap.reservas_restaurantes.aplicacion.controller.dto.output.ReservaOutput;
import com.fiap.reservas_restaurantes.aplicacion.ports.CriarReservaRestauranteUseCasePorts;
import com.fiap.reservas_restaurantes.aplicacion.ports.dto.ReservaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/criar-reservas")
@RequiredArgsConstructor
public class ReservaController {

  private final CriarReservaRestauranteUseCasePorts criarReservaRestauranteUseCasePorts;

  @PostMapping
  public ResponseEntity<ReservaOutput> criarReserva(@RequestBody ReservaInput reservaInput){
    ReservaDTO reservaDTO = criarReservaRestauranteUseCasePorts.criarReserva(reservaInput.reservaInputToDTO());
    return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(reservaDTO.toOutput());
  }

}
