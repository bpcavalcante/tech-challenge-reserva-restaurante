package com.fiap.reservas_restaurantes.infraestructure.entities;

import com.fiap.reservas_restaurantes.domain.Reserva;
import com.fiap.reservas_restaurantes.domain.ports.dto.ReservaDatabaseDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_RESERVA")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ReservaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;
  private String email;
  private Integer quantidadePessoas;
  private LocalDateTime dataHoraReserva;
  private String status;

  @ManyToOne
  @JoinColumn(name = "restaurante_id")
  private RestauranteEntity restaurante;

  public ReservaDatabaseDTO toDatabaseDTO() {
    return ReservaDatabaseDTO.builder()
        .id(this.id)
        .nome(this.nome)
        .email(this.email)
        .quantidadePessoas(this.quantidadePessoas)
        .dataHoraReserva(this.dataHoraReserva)
        .status(this.status)
        .restauranteId(this.restaurante != null ? this.restaurante.getId() : null)
        .build();
  }

  public Reserva toDomain() {
    return Reserva.builder()
        .id(this.id)
        .nome(this.nome)
        .email(this.email)
        .quantidadePessoas(this.quantidadePessoas)
        .dataHoraReserva(this.dataHoraReserva)
        .status(this.status)
        .restauranteId(this.restaurante.getId())
        .build();
  }
}
