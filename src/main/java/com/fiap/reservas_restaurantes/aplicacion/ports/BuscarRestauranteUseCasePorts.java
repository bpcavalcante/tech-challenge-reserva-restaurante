package com.fiap.reservas_restaurantes.aplicacion.ports;

import com.fiap.reservas_restaurantes.aplicacion.controller.dto.output.RestauranteOutput;
import java.util.List;

public interface BuscarRestauranteUseCasePorts {
  List<RestauranteOutput> buscar(String nome, String localizacao, String tipoCozinha);
}
