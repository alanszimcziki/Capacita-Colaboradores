package br.com.alan.Capacita.Colaboradores.controller.response;

import br.com.alan.Capacita.Colaboradores.enums.StatusTreinamento;

public record CriarTreinamentoResponse(Long id, String titulo, String descricao, StatusTreinamento status) {
}
