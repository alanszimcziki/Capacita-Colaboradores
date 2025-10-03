package br.com.alan.Capacita.Colaboradores.controller.response;

import br.com.alan.Capacita.Colaboradores.enums.StatusInscricao;
import br.com.alan.Capacita.Colaboradores.model.Colaborador;
import br.com.alan.Capacita.Colaboradores.model.Treinamento;
import java.util.Date;

public record CriarInscricaoResponse(Long id, Date data, StatusInscricao statusInscricao,
                                     Colaborador colaborador, Treinamento treinamento) {
}
