package com.fiap.reservas_restaurantes.aplicacion.ports;

import com.fiap.reservas_restaurantes.aplicacion.ports.dto.AvaliacaoDTO;

public interface CadastrarAvaliacaoUseCasePorts {
  AvaliacaoDTO cadastrar(AvaliacaoDTO avaliacaoDTO);

}
