package com.fiap.reservas_restaurantes.domain.ports.dto;

import com.fiap.reservas_restaurantes.aplicacion.ports.dto.AvaliacaoDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AvaliacaoDatabaseDTO {
  private Long id;
  private Double nota;
  private String comentario;
  private Long restauranteId;

  public AvaliacaoDTO toDTO() {
    return AvaliacaoDTO.builder()
        .id(this.id)
        .nota(this.nota)
        .comentario(this.comentario)
        .restauranteId(this.restauranteId)
        .build();
  }
}
