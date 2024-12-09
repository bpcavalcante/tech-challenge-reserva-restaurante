package com.fiap.reservas_restaurantes.infraestructure.entities;

import com.fiap.reservas_restaurantes.domain.ports.dto.AvaliacaoDatabaseDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_AVALIACAO")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AvaliacaoEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Double nota;
  private String comentario;
  @ManyToOne
  @JoinColumn(name = "restaurante_id")
  private RestauranteEntity restaurante;

  public AvaliacaoDatabaseDTO toDatabaseDTO() {
    return AvaliacaoDatabaseDTO.builder()
        .id(this.id)
        .nota(this.nota)
        .comentario(this.comentario)
        .restauranteId(this.restaurante != null ? this.restaurante.getId() : null)
        .build();
  }
}
