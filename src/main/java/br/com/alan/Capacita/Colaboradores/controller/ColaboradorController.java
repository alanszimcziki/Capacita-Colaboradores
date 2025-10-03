package br.com.alan.Capacita.Colaboradores.controller;

import br.com.alan.Capacita.Colaboradores.controller.mapper.ColaboradorMapper;
import br.com.alan.Capacita.Colaboradores.controller.request.CriarColaboradorRequest;
import br.com.alan.Capacita.Colaboradores.controller.response.CriarColaboradorResponse;
import br.com.alan.Capacita.Colaboradores.model.Colaborador;
import br.com.alan.Capacita.Colaboradores.service.CapacitaColaboradorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/colaborador")
public class ColaboradorController {

    private final CapacitaColaboradorService capacitaColaboradorService;

    public ColaboradorController(CapacitaColaboradorService capacitaColaboradorService) {
        this.capacitaColaboradorService = capacitaColaboradorService;
    }

    //Cadastra Colaborador
    @PostMapping
    public ResponseEntity<CriarColaboradorResponse> cadastraColaborador(@RequestBody @Valid CriarColaboradorRequest request){
        Colaborador colaborador = ColaboradorMapper.toDomainColaborador(request);
        Colaborador colaboradorCriado = capacitaColaboradorService.cadastraColaborador(colaborador);
        CriarColaboradorResponse response = ColaboradorMapper.toDomaindColaboradorResponse(colaboradorCriado);
        return ResponseEntity.ok().body(response);
    }








}
