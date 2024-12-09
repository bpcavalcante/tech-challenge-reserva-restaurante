package com.fiap.reservas_restaurantes.aplicacion.ports.dto;

import com.fiap.reservas_restaurantes.aplicacion.controller.dto.output.RestauranteOutput;
import com.fiap.reservas_restaurantes.domain.Restaurante;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RestauranteDTO {
  private Long id;
  private String nome;
  private String localizacao;
  private String tipoCozinha;
  private Integer capacidade;
  private LocalDateTime horarioAbertura;
  private LocalDateTime horarioFechamento;

  public RestauranteOutput toOutput() {
    return RestauranteOutput.builder()
        .id(this.id)
        .nome(this.nome)
        .localizacao(this.localizacao)
        .tipoCozinha(this.tipoCozinha)
        .capacidade(this.capacidade)
        .horarioAbertura(this.horarioAbertura)
        .horarioFechamento(this.horarioFechamento)
        .build();
  }

  public Restaurante toDomain() {
    return Restaurante.builder()
        .id(this.id)
        .nome(this.nome)
        .localizacao(this.localizacao)
        .tipoCozinha(this.tipoCozinha)
        .capacidade(this.capacidade)
        .horarioAbertura(this.horarioAbertura)
        .horarioFechamento(this.horarioFechamento)
        .build();
  }
}