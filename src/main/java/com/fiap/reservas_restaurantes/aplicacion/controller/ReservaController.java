package com.fiap.reservas_restaurantes.aplicacion.controller;

import com.fiap.reservas_restaurantes.aplicacion.controller.dto.input.ReservaInput;
import com.fiap.reservas_restaurantes.aplicacion.controller.dto.output.ReservaOutput;
import com.fiap.reservas_restaurantes.aplicacion.ports.BuscarReservaUseCasePorts;
import com.fiap.reservas_restaurantes.aplicacion.ports.CriarReservaRestauranteUseCasePorts;
import com.fiap.reservas_restaurantes.aplicacion.ports.dto.ReservaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/reservas")
@RequiredArgsConstructor
public class ReservaController {

  private final CriarReservaRestauranteUseCasePorts criarReservaRestauranteUseCasePorts;
  private final BuscarReservaUseCasePorts buscarReservaUseCasePorts;

  @PostMapping
  public ResponseEntity<ReservaOutput> criarReserva(@RequestBody ReservaInput reservaInput){
    ReservaDTO reservaDTO = criarReservaRestauranteUseCasePorts.criarReserva(reservaInput.reservaInputToDTO());
    return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(reservaDTO.toOutput());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ReservaOutput> visualizarReserva(@PathVariable Long id){
    ReservaDTO reservaDTO = buscarReservaUseCasePorts.buscarReserva(id);
    return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(reservaDTO.toOutput());
  }

}
