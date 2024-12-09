package com.fiap.reservas_restaurantes.aplicacion.controller.dto.output;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AvaliacaoOutput {
  private Long id;
  private Double nota;
  private String comentario;
}
