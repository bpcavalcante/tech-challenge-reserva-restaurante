package com.fiap.reservas_restaurantes.infraestructure.entities;

import com.fiap.reservas_restaurantes.domain.ports.dto.RestauranteDatabaseDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_RESTAURANTE")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class RestauranteEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private String localizacao;
  private String tipoCozinha;
  private Integer capacidade;
  private LocalDateTime horarioAbertura;
  private LocalDateTime horarioFechamento;
  @OneToMany(mappedBy = "restaurante", fetch = FetchType.LAZY)
  private List<AvaliacaoEntity> avaliacoes;
  @OneToMany(mappedBy = "restaurante", fetch = FetchType.LAZY)
  private List<ReservaEntity> reservas;

  public RestauranteDatabaseDTO toDatabaseDTO() {
    return RestauranteDatabaseDTO.builder()
        .id(this.id)
        .nome(this.nome)
        .localizacao(this.localizacao)
        .tipoCozinha(this.tipoCozinha)
        .capacidade(this.capacidade)
        .horarioAbertura(this.horarioAbertura)
        .horarioFechamento(this.horarioFechamento)
        .avaliacoes(this.avaliacoes != null ?
            this.avaliacoes.stream()
                .map(AvaliacaoEntity::toDatabaseDTO)
                .collect(Collectors.toList())
            : null)
        .build();
  }


}
