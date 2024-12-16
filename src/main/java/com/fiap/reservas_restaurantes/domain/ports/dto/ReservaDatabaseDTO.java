package com.fiap.reservas_restaurantes.domain.ports.dto;

import com.fiap.reservas_restaurantes.aplicacion.ports.dto.ReservaDTO;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReservaDatabaseDTO {
  private Long id;
  private String nome;
  private String email;
  private Integer quantidadePessoas;
  private LocalDateTime dataHoraReserva;
  private String status;
  private Long restauranteId;

  public ReservaDTO toDTO() {
    return ReservaDTO.builder()
        .id(this.id)
        .nome(this.nome)
        .email(this.email)
        .quantidadePessoas(this.quantidadePessoas)
        .dataHoraReserva(this.dataHoraReserva)
        .status(this.status)
        .restauranteId(this.restauranteId)
        .build();
  }
}
