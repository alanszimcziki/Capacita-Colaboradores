package br.com.alan.Capacita.Colaboradores.controller.response;

import br.com.alan.Capacita.Colaboradores.enums.StatusTreinamento;


public record CancelarTreinamentoResponse(Long idTreinamento,
                                          String tituloTreinamento,
                                          String descricaoTreinamento,
                                          StatusTreinamento statusTreinamento) {
}

