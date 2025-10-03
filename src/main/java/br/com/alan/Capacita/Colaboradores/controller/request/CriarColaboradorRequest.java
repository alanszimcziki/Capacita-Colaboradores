package br.com.alan.Capacita.Colaboradores.controller.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CriarColaboradorRequest(
        @NotBlank(message = "Nome não informado")
        String nome,

        @NotBlank(message = "Email não informado")
        @Email(message = "Email inválido")
        String email,

        @NotBlank(message = "Cargo não informado")
        String cargo) {
}
