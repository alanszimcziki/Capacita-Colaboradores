package br.com.alan.Capacita.Colaboradores.controller.mapper;

import br.com.alan.Capacita.Colaboradores.controller.request.CriarTreinamentoRequest;
import br.com.alan.Capacita.Colaboradores.controller.response.CancelarTreinamentoResponse;
import br.com.alan.Capacita.Colaboradores.controller.response.CriarTreinamentoResponse;
import br.com.alan.Capacita.Colaboradores.controller.response.FinalizaTreinamentoResponse;
import br.com.alan.Capacita.Colaboradores.controller.response.IniciaTreinamentoResponse;
import br.com.alan.Capacita.Colaboradores.model.Treinamento;

public class TreinamentoMapper {
    //todo: Preciso devolver o método no formato "TREINAMENTO", estou passando ele no formato "REQUEST"
    public static Treinamento toDomainTreinamento(CriarTreinamentoRequest request){
        Treinamento treinamento = new Treinamento(request.titulo(),request.descricao(),request.status());
        return treinamento;
    }
    //todo: Preciso devolver o método no formato "RESPONSE", estou passando no formato "TREINAMENTO"
    public static CriarTreinamentoResponse toDomainTreinamentoResponse(Treinamento request){
        return new CriarTreinamentoResponse(request.getId(),request.getTitulo()
                ,request.getDescricao(),request.getStatus());
    }

    public static CancelarTreinamentoResponse toDomainCancelaTreinamentoResponse(Treinamento request){
        return new CancelarTreinamentoResponse(request.getId(),
                request.getTitulo(), request.getDescricao(), request.getStatus());

    }
    public static FinalizaTreinamentoResponse toDomainFinalizaTreinamentoResponse(Treinamento request){
        return new FinalizaTreinamentoResponse(request.getId(), request.getTitulo(),
                request.getDescricao(), request.getStatus());
    }
    public static IniciaTreinamentoResponse toDomainIniciaTreinamentoResponse(Treinamento request){
        return new IniciaTreinamentoResponse(request.getId(), request.getTitulo(), request.getDescricao(),
                request.getStatus());

    }
}
