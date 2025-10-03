package br.com.alan.Capacita.Colaboradores.service;

import br.com.alan.Capacita.Colaboradores.enums.StatusInscricao;
import br.com.alan.Capacita.Colaboradores.enums.StatusTreinamento;
import br.com.alan.Capacita.Colaboradores.exceptions.*;
import br.com.alan.Capacita.Colaboradores.model.Colaborador;
import br.com.alan.Capacita.Colaboradores.model.Inscricao;
import br.com.alan.Capacita.Colaboradores.model.Treinamento;
import br.com.alan.Capacita.Colaboradores.repository.ColaboradorRepository;
import br.com.alan.Capacita.Colaboradores.repository.InscricaoRepository;
import br.com.alan.Capacita.Colaboradores.repository.TreinamentoRepository;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class CapacitaColaboradorService {
    private final ColaboradorRepository colaboradorRepository;
    private final TreinamentoRepository treinamentoRepository;
    private final InscricaoRepository inscricaoRepository;

    public CapacitaColaboradorService(ColaboradorRepository colaboradorRepository, TreinamentoRepository treinamentoRepository, InscricaoRepository inscricaoRepository) {
        this.colaboradorRepository = colaboradorRepository;
        this.treinamentoRepository = treinamentoRepository;
        this.inscricaoRepository = inscricaoRepository;
    }

    public Colaborador cadastraColaborador(Colaborador colaborador) {
        return colaboradorRepository.save(colaborador); //Colaborador Cadastrado
    }

    public Treinamento cadastraTreinamento(Treinamento treinamento){
        return treinamentoRepository.save(treinamento);
    }

    public Treinamento iniciarTreinamento(Long idTreinamento){
        Treinamento treinamento = treinamentoRepository.findById(idTreinamento).orElseThrow(() ->
                new TreinamentoNaoEncontradoException(String.format("Treinamento não encontrado!")));
        if(treinamento.getStatus() != StatusTreinamento.NAO_INICIADO){
            throw new TreinamentoNaoEncontradoException(String.format("Treinamento não pode ser encontrado porque ele não está em NÃO INICIADO!"));
        }
        treinamento.setStatus(StatusTreinamento.ANDAMENTO);
        return treinamentoRepository.save(treinamento);

    }
    public Treinamento finalizarTreinamento(Long idTreinamento){
        Treinamento treinamento = treinamentoRepository.findById(idTreinamento).orElseThrow(() ->
                new TreinamentoNaoEncontradoException(String.format("Treinamento não encontrado")));

        if(!StatusTreinamento.ANDAMENTO.equals(treinamento.getStatus())){
            throw new TreinamentoNaoEncontradoException(String.format("Treinamento não pode ser encontrado porque ele não está em ANDAMENTO!"));
        }
        treinamento.setStatus(StatusTreinamento.FINALIZADO);
        return treinamentoRepository.save(treinamento);
    }


    public Treinamento cancelaTreinamento(Long idTreinamento){
        Treinamento treinamento = treinamentoRepository.findById(idTreinamento).orElseThrow(() ->
                new TreinamentoNaoEncontradoException(String.format("Treinamento não encontrado!")));

        if(treinamento.getStatus() != StatusTreinamento.NAO_INICIADO){
            throw new TreinamentoNaoEncontradoException(String.format("Treinamento não pode ser encontrado porque não foi iniciado!"));
        }
        treinamento.setStatus(StatusTreinamento.CANCELADO);
        return treinamentoRepository.save(treinamento);

    }

    public Inscricao cadastraInscricao(Long idTreinamento, String emailColaborador){

        // buscar no repository de treinamento o treinamento com esse id
        Optional<Treinamento> buscaTreinamento = treinamentoRepository.findById(idTreinamento);

        //Treinamento não encontrado
        if(buscaTreinamento.isEmpty()){
            throw new TreinamentoNaoEncontradoException(String.format("Treinamento não encontrado!"));
        }

        // retorna treinamento encontrado do OPTIONAL
        Treinamento treinamento = buscaTreinamento.get();

        // buscar no repository de colaborador um colaborador com email informado

         Optional<Colaborador> colaborador = colaboradorRepository.findByEmail(emailColaborador);

        //Fazer caso negativo
        if(colaborador.isEmpty()){
            throw new ColaboradorNaoEncontradoException(String.format("Colaborador não encontrado!"));
        }
        Colaborador colaboradorEncontrado = colaborador.get();


        Optional<Inscricao> inscricao = inscricaoRepository.findByTreinamentoIdAndColaboradorIdAndStatus(idTreinamento,colaboradorEncontrado.getId(),StatusInscricao.CONFIRMADO);

        if(inscricao.isPresent()){
        throw new ColaboradorJaInscritoNoTreinamentoException(String.format("O Colaborador " + colaboradorEncontrado.getNome() + " já está inscrito no treinamento !" + treinamento.getTitulo()));
        }

        // instancear uma inscricao com a data atual, status de confirmada, com o treinamento e colaborador encontrado
        Inscricao inscricaoEncontrada = new Inscricao(
                new Date(),
                StatusInscricao.CONFIRMADO,
                colaboradorEncontrado,treinamento);

        // Com repository de inscriçao salvar a inscricao que foi instanceada
        return inscricaoRepository.save(inscricaoEncontrada);

    }

    public List<Colaborador> consultaColaboradoresInscricoesAtivas(Long idTreinamento){
        List<Inscricao> listaInscricoes = inscricaoRepository.findByTreinamentoIdAndStatus(idTreinamento,StatusInscricao.CONFIRMADO);

        if(listaInscricoes.isEmpty()){
            // Lista vazia
            return Collections.emptyList();
        }

        List<Colaborador> colaboradores = new ArrayList<>();

        for (Inscricao inscricao : listaInscricoes ){
            Colaborador colaborador =  inscricao.getColaborador();
            colaboradores.add(colaborador);

        }
        return colaboradores;






    }
}
