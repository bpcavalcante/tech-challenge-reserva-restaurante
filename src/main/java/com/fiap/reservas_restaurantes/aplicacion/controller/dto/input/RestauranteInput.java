package com.fiap.reservas_restaurantes.aplicacion.controller.dto.input;

import com.fiap.reservas_restaurantes.aplicacion.ports.dto.RestauranteDTO;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RestauranteInput {
  private String nome;
  private String localizacao;
  private String tipoCozinha;
  private Integer capacidade;
  private LocalDateTime horarioAbertura;
  private LocalDateTime horarioFechamento;

  public RestauranteDTO restauranteInputToDTO() {
    return RestauranteDTO.builder()
        .nome(this.nome)
        .localizacao(this.localizacao)
        .tipoCozinha(this.tipoCozinha)
        .capacidade(this.capacidade)
        .horarioAbertura(this.horarioAbertura)
        .horarioFechamento(this.horarioFechamento)
        .build();
  }
}
