package com.fiap.reservas_restaurantes.aplicacion.controller.dto.output;

import com.fiap.reservas_restaurantes.aplicacion.ports.dto.AvaliacaoDTO;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RestauranteOutput {
  private Long id;
  private String nome;
  private String localizacao;
  private String tipoCozinha;
  private Integer capacidade;
  private LocalDateTime horarioAbertura;
  private LocalDateTime horarioFechamento;
  private List<AvaliacaoOutput> avaliacoes;
}
