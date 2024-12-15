package com.fiap.reservas_restaurantes.aplicacion.ports.dto;

import com.fiap.reservas_restaurantes.aplicacion.controller.dto.output.ReservaOutput;
import com.fiap.reservas_restaurantes.domain.Reserva;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReservaDTO {
  private Long id;
  private String nome;
  private String email;
  private Integer quantidadePessoas;
  private LocalDateTime dataHoraReserva;
  private Long restauranteId;

  public ReservaOutput toOutput() {
    return ReservaOutput.builder().id(this.id).nome(this.nome).email(this.email)
        .quantidadePessoas(this.quantidadePessoas).dataHoraReserva(this.dataHoraReserva).restauranteId(this.restauranteId).build();
  }

  public Reserva toDomain() {
    return Reserva.builder().id(this.id).nome(this.nome).email(this.email)
        .quantidadePessoas(this.quantidadePessoas)
        .dataHoraReserva(this.dataHoraReserva)
        .restauranteId(this.restauranteId).build();
  }
}
