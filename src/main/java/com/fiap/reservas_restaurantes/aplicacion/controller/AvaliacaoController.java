package com.fiap.reservas_restaurantes.aplicacion.controller;

import com.fiap.reservas_restaurantes.aplicacion.controller.dto.input.AvaliacaoInput;
import com.fiap.reservas_restaurantes.aplicacion.controller.dto.output.AvaliacaoOutput;
import com.fiap.reservas_restaurantes.aplicacion.ports.CadastrarAvaliacaoUseCasePorts;
import com.fiap.reservas_restaurantes.aplicacion.ports.dto.AvaliacaoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/avaliacoes")
@RequiredArgsConstructor
public class AvaliacaoController {

  private final CadastrarAvaliacaoUseCasePorts cadastrarAvaliacaoUseCasePorts;

  @PostMapping
  public ResponseEntity<AvaliacaoOutput> cadastrar(@RequestBody AvaliacaoInput avaliacaoInput) {
    AvaliacaoDTO avaliacaoDTO =
        cadastrarAvaliacaoUseCasePorts.cadastrar(avaliacaoInput.toDTO());
    return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(avaliacaoDTO.toOutput());
  }
}
