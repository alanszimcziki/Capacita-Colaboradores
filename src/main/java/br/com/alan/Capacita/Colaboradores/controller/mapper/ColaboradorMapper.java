package br.com.alan.Capacita.Colaboradores.controller.mapper;

import br.com.alan.Capacita.Colaboradores.controller.request.CriarColaboradorRequest;
import br.com.alan.Capacita.Colaboradores.controller.response.CriarColaboradorResponse;
import br.com.alan.Capacita.Colaboradores.model.Colaborador;

public class ColaboradorMapper {

    public static Colaborador toDomainColaborador(CriarColaboradorRequest request){
         Colaborador colaborador = new Colaborador(request.nome(),request.email(),request.cargo());
         return colaborador;
    }
    public static CriarColaboradorResponse toDomaindColaboradorResponse(Colaborador colaborador){
        return new CriarColaboradorResponse(
                colaborador.getId(), colaborador.getNome(),
                colaborador.getEmail(), colaborador.getCargo());
    }

}
