package com.fiap.reservas_restaurantes.aplicacion.controller.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AvaliacaoOutput {
  private Long id;
  private Double nota;
  private String comentario;
}
