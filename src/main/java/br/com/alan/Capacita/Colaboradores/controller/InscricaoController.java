package br.com.alan.Capacita.Colaboradores.controller;

import br.com.alan.Capacita.Colaboradores.controller.mapper.InscricaoMapper;
import br.com.alan.Capacita.Colaboradores.controller.request.ConsultaColaboradoresTreinamentoRequest;
import br.com.alan.Capacita.Colaboradores.controller.request.CriarInscricaoTreinamentoRequest;
import br.com.alan.Capacita.Colaboradores.controller.response.ConsultaColaboradoresTreinamentoResponse;
import br.com.alan.Capacita.Colaboradores.controller.response.CriarInscricaoResponse;
import br.com.alan.Capacita.Colaboradores.model.Colaborador;
import br.com.alan.Capacita.Colaboradores.model.Inscricao;
import br.com.alan.Capacita.Colaboradores.service.CapacitaColaboradorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscricao")
public class InscricaoController {

    private final CapacitaColaboradorService capacitaColaboradorService;

    public InscricaoController(CapacitaColaboradorService capacitaColaboradorService) {
        this.capacitaColaboradorService = capacitaColaboradorService;
    }

    //Cadastra Incrição de um Colaborador em um Treinamento
    @PostMapping
    public ResponseEntity<CriarInscricaoResponse> cadastraIncricaoColaboradorEmUmTreinamento(@RequestBody CriarInscricaoTreinamentoRequest request){
        Inscricao inscricao = capacitaColaboradorService.cadastraInscricao(request.idTreinamento(), request.email());
        CriarInscricaoResponse response = InscricaoMapper.toDomainInscricaoTreinamentoResponse(inscricao);
        return ResponseEntity.ok().body(response);
    }
    //Consulta Colaboradores Inscritos em um Treinamento
    @GetMapping("/treinamento/{idTreinamento}")
    public ResponseEntity<ConsultaColaboradoresTreinamentoResponse> consultaColaboradorInscrito(@PathVariable Long idTreinamento){
        List<Colaborador> colaboradores = capacitaColaboradorService.consultaColaboradoresInscricoesAtivas(idTreinamento);
        if(colaboradores == null || colaboradores.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        ConsultaColaboradoresTreinamentoResponse response = InscricaoMapper.consultaColaboradorInscritoMapper(colaboradores);
        return ResponseEntity.ok().body(response);

    }

    //Testes Unitários





}
