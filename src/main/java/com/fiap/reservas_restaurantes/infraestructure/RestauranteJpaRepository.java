package com.fiap.reservas_restaurantes.infraestructure;

import com.fiap.reservas_restaurantes.infraestructure.entities.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteJpaRepository extends JpaRepository<RestauranteEntity, Long> {
}
