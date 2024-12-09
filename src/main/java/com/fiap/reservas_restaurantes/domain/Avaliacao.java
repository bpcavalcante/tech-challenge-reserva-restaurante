package com.fiap.reservas_restaurantes.domain;

import com.fiap.reservas_restaurantes.domain.ports.dto.AvaliacaoDatabaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Avaliacao {
  private Long id;
  private Double nota;
  private String comentario;
  private Long restauranteId;

  public AvaliacaoDatabaseDTO toDTO() {
    return AvaliacaoDatabaseDTO.builder()
        .id(this.id)
        .nota(this.nota)
        .comentario(this.comentario)
        .restauranteId(this.restauranteId)
        .build();
  }
}
