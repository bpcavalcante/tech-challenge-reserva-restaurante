package com.fiap.reservas_restaurantes.infraestructure;

import com.fiap.reservas_restaurantes.infraestructure.entities.RestauranteEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RestauranteJpaRepository extends JpaRepository<RestauranteEntity, Long> {

  @Query("SELECT r FROM RestauranteEntity r " +
      "LEFT JOIN FETCH r.avaliacoes a " +
      "WHERE (:nome IS NULL OR LOWER(r.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
      "(:localizacao IS NULL OR LOWER(r.localizacao) LIKE LOWER(CONCAT('%', :localizacao, '%'))) AND " +
      "(:tipoCozinha IS NULL OR LOWER(r.tipoCozinha) LIKE LOWER(CONCAT('%', :tipoCozinha, '%')))")
  List<RestauranteEntity> buscarComAvaliacoes(String nome, String localizacao, String tipoCozinha);


}
