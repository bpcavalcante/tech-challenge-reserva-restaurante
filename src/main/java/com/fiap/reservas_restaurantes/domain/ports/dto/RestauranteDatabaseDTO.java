package com.fiap.reservas_restaurantes.domain.ports.dto;

import com.fiap.reservas_restaurantes.aplicacion.ports.dto.RestauranteDTO;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class RestauranteDatabaseDTO {
  private Long id;
  private String nome;
  private String localizacao;
  private String tipoCozinha;
  private Integer capacidade;
  private LocalDateTime horarioAbertura;
  private LocalDateTime horarioFechamento;
  private List<AvaliacaoDatabaseDTO> avaliacoes;

  public RestauranteDTO toDTO() {
    return RestauranteDTO.builder()
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
