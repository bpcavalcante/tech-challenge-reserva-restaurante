package com.fiap.reservas_restaurantes.aplicacion.controller.dto.output;

import java.time.LocalDateTime;
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
public class ReservaOutput {
  private Long id;
  private String nome;
  private String email;
  private Integer quantidadePessoas;
  private LocalDateTime dataHoraReserva;
  private String status;
  private Long restauranteId;
}
