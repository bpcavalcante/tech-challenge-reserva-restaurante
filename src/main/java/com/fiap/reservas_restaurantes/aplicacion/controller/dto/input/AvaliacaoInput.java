package com.fiap.reservas_restaurantes.aplicacion.controller.dto.input;

import com.fiap.reservas_restaurantes.aplicacion.ports.dto.AvaliacaoDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AvaliacaoInput {
  private Double nota;
  private String comentario;
  private Long restauranteId;

  public AvaliacaoDTO toDTO() {
    return AvaliacaoDTO.builder()
        .nota(this.nota)
        .comentario(this.comentario)
        .restauranteId(this.restauranteId)
        .build();
  }
}
