package com.fiap.reservas_restaurantes.domain.usecase;

import com.fiap.reservas_restaurantes.aplicacion.ports.CadastrarAvaliacaoUseCasePorts;
import com.fiap.reservas_restaurantes.aplicacion.ports.dto.AvaliacaoDTO;
import com.fiap.reservas_restaurantes.domain.Avaliacao;
import com.fiap.reservas_restaurantes.domain.ports.AvaliacaoRepositoryPort;
import com.fiap.reservas_restaurantes.domain.ports.dto.AvaliacaoDatabaseDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CadastrarAvaliacaoUseCase implements CadastrarAvaliacaoUseCasePorts {

  private final AvaliacaoRepositoryPort avaliacaoRepositoryPort;

  @Override
  public AvaliacaoDTO cadastrar(AvaliacaoDTO avaliacaoDTO) {
    Avaliacao avaliacao = avaliacaoDTO.toDomain();
    AvaliacaoDatabaseDTO avaliacaoDatabaseDTO = avaliacaoRepositoryPort.save(avaliacao.toDTO());
    return avaliacaoDatabaseDTO.toDTO();
  }
}
