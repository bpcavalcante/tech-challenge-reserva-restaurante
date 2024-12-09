package com.fiap.reservas_restaurantes.domain.ports;

import com.fiap.reservas_restaurantes.domain.ports.dto.AvaliacaoDatabaseDTO;

public interface AvaliacaoRepositoryPort {
  AvaliacaoDatabaseDTO save(AvaliacaoDatabaseDTO avaliacaoDatabaseDTO);
}
