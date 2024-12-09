package com.fiap.reservas_restaurantes.infraestructure.implementations;

import com.fiap.reservas_restaurantes.domain.ports.AvaliacaoRepositoryPort;
import com.fiap.reservas_restaurantes.domain.ports.dto.AvaliacaoDatabaseDTO;
import com.fiap.reservas_restaurantes.infraestructure.AvaliacaoJpaRepository;
import com.fiap.reservas_restaurantes.infraestructure.RestauranteJpaRepository;
import com.fiap.reservas_restaurantes.infraestructure.entities.AvaliacaoEntity;
import com.fiap.reservas_restaurantes.infraestructure.entities.RestauranteEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;

@RequiredArgsConstructor
public class AvaliacaoSqlRepositoryImpl implements AvaliacaoRepositoryPort {

  private final AvaliacaoJpaRepository avaliacaoJpaRepository;
  private final RestauranteJpaRepository restauranteJpaRepository;

  @Override
  public AvaliacaoDatabaseDTO save(AvaliacaoDatabaseDTO avaliacaoDatabaseDTO) {
    try {
      RestauranteEntity restaurante =
          restauranteJpaRepository.findById(avaliacaoDatabaseDTO.getRestauranteId())
              .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

      AvaliacaoEntity entity =
          AvaliacaoEntity.builder()
              .nota(avaliacaoDatabaseDTO.getNota())
              .comentario(avaliacaoDatabaseDTO.getComentario())
              .restaurante(restaurante) // Associação com o restaurante
              .build();

      return avaliacaoJpaRepository.save(entity).toDatabaseDTO();

    } catch (DataAccessException e) {
      throw new RuntimeException("Erro ao inserir avaliacao", e);
    }
  }
}
