package br.com.alan.Capacita.Colaboradores.controller.mapper;
import br.com.alan.Capacita.Colaboradores.controller.response.ConsultaColaboradoresTreinamentoResponse;
import br.com.alan.Capacita.Colaboradores.controller.response.CriarInscricaoResponse;
import br.com.alan.Capacita.Colaboradores.model.Colaborador;
import br.com.alan.Capacita.Colaboradores.model.Inscricao;

import java.util.List;

public class InscricaoMapper {

    public static CriarInscricaoResponse toDomainInscricaoTreinamentoResponse(Inscricao inscricao){
        return new CriarInscricaoResponse(inscricao.getId(),
                inscricao.getData(),inscricao.getStatus(),
                inscricao.getColaborador(),inscricao.getTreinamento());

    }
    public static ConsultaColaboradoresTreinamentoResponse consultaColaboradorInscritoMapper(List<Colaborador> colaboradores){
        return new ConsultaColaboradoresTreinamentoResponse(colaboradores);
    }

}
