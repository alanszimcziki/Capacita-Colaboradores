package br.com.alan.Capacita.Colaboradores.controller;

import br.com.alan.Capacita.Colaboradores.controller.mapper.TreinamentoMapper;
import br.com.alan.Capacita.Colaboradores.controller.request.CancelarTreinamentoRequest;
import br.com.alan.Capacita.Colaboradores.controller.request.CriarTreinamentoRequest;
import br.com.alan.Capacita.Colaboradores.controller.request.FinalizaTreinamentoRequest;
import br.com.alan.Capacita.Colaboradores.controller.request.IniciaTreinamentoRequest;
import br.com.alan.Capacita.Colaboradores.controller.response.CancelarTreinamentoResponse;
import br.com.alan.Capacita.Colaboradores.controller.response.CriarTreinamentoResponse;
import br.com.alan.Capacita.Colaboradores.controller.response.FinalizaTreinamentoResponse;
import br.com.alan.Capacita.Colaboradores.controller.response.IniciaTreinamentoResponse;
import br.com.alan.Capacita.Colaboradores.model.Treinamento;
import br.com.alan.Capacita.Colaboradores.service.CapacitaColaboradorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/treinamento")
public class TreinamentoController {

    private final CapacitaColaboradorService capacitaColaboradorService;

    public TreinamentoController(CapacitaColaboradorService capacitaColaboradorService) {
        this.capacitaColaboradorService = capacitaColaboradorService;
    }
    //Cadastra Treinamento
    @PostMapping
    public ResponseEntity<CriarTreinamentoResponse> cadastraTreinamento(@RequestBody @Valid CriarTreinamentoRequest request){
        Treinamento treinamento = TreinamentoMapper.toDomainTreinamento(request);
        Treinamento treinamentoCriado = capacitaColaboradorService.cadastraTreinamento(treinamento);
        CriarTreinamentoResponse response = TreinamentoMapper.toDomainTreinamentoResponse(treinamentoCriado);
        return ResponseEntity.ok().body(response);
    }
    //Iniciar Treinamento
    @PostMapping("/inicia")
    public ResponseEntity<IniciaTreinamentoResponse> iniciaTreinamento(@RequestBody IniciaTreinamentoRequest request){
        Treinamento iniciaTreinamento = capacitaColaboradorService.iniciarTreinamento(request.idTreinamento());
        IniciaTreinamentoResponse treinamentoResponse= TreinamentoMapper.toDomainIniciaTreinamentoResponse(iniciaTreinamento);
        return ResponseEntity.ok().body(treinamentoResponse);
    }

    //Finalizar Treinamento
    @PostMapping("/finaliza")
    public ResponseEntity<FinalizaTreinamentoResponse> finalizaTreinamento(@RequestBody FinalizaTreinamentoRequest request){
        Treinamento finalizaTreinamento = capacitaColaboradorService.finalizarTreinamento(request.idTreinamento());
        FinalizaTreinamentoResponse response = TreinamentoMapper.toDomainFinalizaTreinamentoResponse(finalizaTreinamento);
        return ResponseEntity.ok().body(response);
    }
    //Cancelar Treinamento
    @PostMapping("/cancelar")
    public ResponseEntity<CancelarTreinamentoResponse> cancelarTreinamento(@RequestBody CancelarTreinamentoRequest request){
        Treinamento cancelaTreinamento = capacitaColaboradorService.cancelaTreinamento(request.idTreinamento());
        CancelarTreinamentoResponse response = TreinamentoMapper.toDomainCancelaTreinamentoResponse(cancelaTreinamento);
        return ResponseEntity.ok().body(response);
    }

    //Criar um endpoint para iniciar um treinamento -> mudar status para ANDAMENTO, SOMENTE SE O STATUS FOR NAO INICIADO
    //Criar um endpoint para finalizar o treinamento -> mudar o status para FINALIZADO, somente fazer isso se o status estiver em ANDAMENTO
    //Endpoint de cancelar um treinamento, -> mudar o status para CANCELADO, somente fazer isso se tiver com o status de não iniciado











}
