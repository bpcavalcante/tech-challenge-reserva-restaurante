package com.fiap.reservas_restaurantes.domain;

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
  private Long restauranteId;

  public ReservaDatabaseDTO toDTO() {
    return ReservaDatabaseDTO.builder().id(this.id).nome(this.nome).email(this.email)
        .quantidadePessoas(this.quantidadePessoas).dataHoraReserva(this.dataHoraReserva).restauranteId(this.restauranteId).build();
  }
}
