package com.fiap.reservas_restaurantes.aplicacion.ports.dto;

import com.fiap.reservas_restaurantes.aplicacion.controller.dto.output.AvaliacaoOutput;
import com.fiap.reservas_restaurantes.domain.Avaliacao;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AvaliacaoDTO {
  private Long id;
  private Double nota;
  private String comentario;
  private Long restauranteId;

  public AvaliacaoOutput toOutput() {
    return AvaliacaoOutput.builder()
        .id(this.id)
        .nota(this.nota)
        .comentario(this.comentario)
        .build();
  }

  public Avaliacao toDomain() {
    return Avaliacao.builder()
        .id(this.id)
        .nota(this.nota)
        .comentario(this.comentario)
        .restauranteId(this.restauranteId)
        .build();
  }
}
