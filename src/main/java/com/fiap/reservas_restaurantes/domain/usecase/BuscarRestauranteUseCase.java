package com.fiap.reservas_restaurantes.domain.usecase;

import com.fiap.reservas_restaurantes.aplicacion.controller.dto.output.RestauranteOutput;
import com.fiap.reservas_restaurantes.aplicacion.ports.BuscarRestauranteUseCasePorts;
import com.fiap.reservas_restaurantes.domain.ports.RestauranteRepositoryPort;
import com.fiap.reservas_restaurantes.domain.ports.dto.RestauranteDatabaseDTO;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuscarRestauranteUseCase implements BuscarRestauranteUseCasePorts {

  private final RestauranteRepositoryPort restauranteRepository;

  @Override
  public List<RestauranteOutput> buscar(String nome, String localizacao, String tipoCozinha) {

    List<RestauranteDatabaseDTO> restaurantes =
        restauranteRepository.buscar(nome, localizacao, tipoCozinha);

    return restaurantes.stream()
        .map(
            restaurante ->
                RestauranteOutput.builder()
                    .id(restaurante.getId())
                    .nome(restaurante.getNome())
                    .localizacao(restaurante.getLocalizacao())
                    .tipoCozinha(restaurante.getTipoCozinha())
                    .capacidade(restaurante.getCapacidade())
                    .horarioAbertura(restaurante.getHorarioAbertura())
                    .horarioFechamento(restaurante.getHorarioFechamento())
                    .build())
        .collect(Collectors.toList());
  }
}
