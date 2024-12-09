package com.fiap.reservas_restaurantes.infraestructure;

import com.fiap.reservas_restaurantes.infraestructure.entities.AvaliacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoJpaRepository extends JpaRepository<AvaliacaoEntity, Integer> {}
