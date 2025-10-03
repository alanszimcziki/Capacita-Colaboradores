package br.com.alan.Capacita.Colaboradores.controller.request;

import br.com.alan.Capacita.Colaboradores.enums.StatusTreinamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CriarTreinamentoRequest(
        @NotBlank(message = "Título não inválido")
        String titulo,

        @NotBlank(message = "Descrição inválida")
        String descricao,

        @NotNull(message = "Status não inválido")
        StatusTreinamento status) {
}
