package com.fiap.reservas_restaurantes.domain;

import com.fiap.reservas_restaurantes.aplicacion.ports.dto.ReservaDTO;
import com.fiap.reservas_restaurantes.domain.ports.dto.ReservaDatabaseDTO;
import java.time.LocalDateTime;
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
public class Reserva {
  private Long id;
  private String nome;
  private String email;
  private Integer quantidadePessoas;
  private LocalDateTime dataHoraReserva;
  private String status;
  private Long restauranteId;

  public ReservaDatabaseDTO toDatabaseDTO() {
    return ReservaDatabaseDTO.builder()
        .id(this.id)
        .nome(this.nome)
        .email(this.email)
        .quantidadePessoas(this.quantidadePessoas)
        .dataHoraReserva(this.dataHoraReserva)
        .status(this.status)
        .restauranteId(this.restauranteId)
        .build();
  }

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
