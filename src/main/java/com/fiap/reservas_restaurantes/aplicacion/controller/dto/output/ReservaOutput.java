package com.fiap.reservas_restaurantes.aplicacion.controller.dto.output;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReservaOutput {
  private Long id;
  private String nome;
  private String email;
  private Integer quantidadePessoas;
  private LocalDateTime dataHoraReserva;
  private Long restauranteId;
}
