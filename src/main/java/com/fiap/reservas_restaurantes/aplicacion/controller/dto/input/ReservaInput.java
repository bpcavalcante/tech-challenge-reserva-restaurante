package com.fiap.reservas_restaurantes.aplicacion.controller.dto.input;

import com.fiap.reservas_restaurantes.aplicacion.ports.dto.ReservaDTO;
import jakarta.validation.constraints.Future;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ReservaInput {
  private String nome;
  private String email;
  private Integer quantidadePessoas;

  @Future(message = "A data e hora da reserva devem ser no futuro")
  private LocalDateTime dataHoraReserva;

  private String status;
  private Long restauranteId;

  public ReservaDTO reservaInputToDTO() {
    return ReservaDTO.builder()
        .nome(this.nome)
        .email(this.email)
        .quantidadePessoas(this.quantidadePessoas)
        .dataHoraReserva(this.dataHoraReserva)
        .status(this.status)
        .restauranteId(this.restauranteId)
        .build();
  }
}
