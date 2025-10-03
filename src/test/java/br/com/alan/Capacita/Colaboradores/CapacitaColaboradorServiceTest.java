package br.com.alan.Capacita.Colaboradores;

import br.com.alan.Capacita.Colaboradores.enums.StatusInscricao;
import br.com.alan.Capacita.Colaboradores.enums.StatusTreinamento;
import br.com.alan.Capacita.Colaboradores.exceptions.TreinamentoNaoEncontradoException;
import br.com.alan.Capacita.Colaboradores.model.Colaborador;
import br.com.alan.Capacita.Colaboradores.model.Inscricao;
import br.com.alan.Capacita.Colaboradores.model.Treinamento;
import br.com.alan.Capacita.Colaboradores.repository.ColaboradorRepository;
import br.com.alan.Capacita.Colaboradores.repository.InscricaoRepository;
import br.com.alan.Capacita.Colaboradores.repository.TreinamentoRepository;
import br.com.alan.Capacita.Colaboradores.service.CapacitaColaboradorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

@ExtendWith(MockitoExtension.class)
public class CapacitaColaboradorServiceTest {

    @Mock
    private ColaboradorRepository colaboradorRepository;
    @Mock
    private InscricaoRepository inscricaoRepository;
    @Mock
    private TreinamentoRepository treinamentoRepository;
    @InjectMocks
    private CapacitaColaboradorService capacitaColaboradorService;


    @Test
    public void testeCadastrarColaboradorComSucesso(){
        //Preparação
        Colaborador colaborador = instanciarColaborador();
        when(colaboradorRepository.save(any(Colaborador.class))).thenReturn(colaborador);

        //Execução
        Colaborador colaboradorCriado = capacitaColaboradorService.cadastraColaborador(colaborador);

        //Validação
        assertNotNull(colaboradorCriado);
        assertEquals(colaboradorCriado.getNome(),colaborador.getNome());
        assertEquals(colaboradorCriado.getEmail(), colaborador.getEmail());
    }
    @Test
    public void testeCadastraTreinamentoComSucesso(){
        //Preparação
        Treinamento treinamento = instanciarTreinamento();
        when(treinamentoRepository.save(any(Treinamento.class))).thenReturn(treinamento);

        //Execução
        Treinamento treinamentoCriado = capacitaColaboradorService.cadastraTreinamento(treinamento);

        //Validação
        assertNotNull(treinamentoCriado);
        assertEquals(treinamentoCriado.getTitulo(),treinamento.getTitulo());
        assertEquals(treinamentoCriado.getDescricao(),treinamento.getDescricao());
    }
    @Test
    public void cadastraInscricaoComSucesso(){
        //Preparação
        Treinamento treinamento = instanciarTreinamento();
        Colaborador colaborador = instanciarColaborador();
        Inscricao inscricao = instancearInscricao();

        when(treinamentoRepository.findById(treinamento.getId())).thenReturn(Optional.of(treinamento));
        when(colaboradorRepository.findByEmail(colaborador.getEmail())).thenReturn(Optional.of(colaborador));
        when(inscricaoRepository.findByTreinamentoIdAndColaboradorIdAndStatus(treinamento.getId(),colaborador.getId(),
                StatusInscricao.CONFIRMADO)).thenReturn(Optional.empty());
        when(inscricaoRepository.save(any(Inscricao.class))).thenReturn(inscricao);

        //Execução
        Inscricao inscricaoCriada = capacitaColaboradorService.cadastraInscricao(treinamento.getId(),colaborador.getEmail());

        //Validação
        assertNotNull(inscricaoCriada);
        assertEquals(inscricaoCriada.getColaborador().getNome(),colaborador.getNome());
        assertEquals(inscricaoCriada.getTreinamento().getDescricao(),treinamento.getDescricao());
        assertEquals(inscricaoCriada.getColaborador().getEmail(), colaborador.getEmail());
        assertEquals(inscricaoCriada.getTreinamento().getStatus(), treinamento.getStatus());
    }
    @Test
    public void cadastraInscricaoTreinamentoNaoEncontrado(){
        //Preparação e Execução
        Colaborador colaborador = instanciarColaborador();
        when(treinamentoRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        //Validação
        TreinamentoNaoEncontradoException e = assertThrows(TreinamentoNaoEncontradoException.class, () -> capacitaColaboradorService.cadastraInscricao(1L, colaborador.getEmail()));
        assertEquals(e.getMessage().toLowerCase(),"Treinamento não encontrado!".toLowerCase());
    }
    public void cadastraInscricaoColaboradorNaoEncontrado(){
        //Preparação
        //Execução
        //Validação
    }
    @Test
    public void consultaColaboradorInscricaoAtivaComSucesso(){
        //Preparação
        Inscricao inscricao = instancearInscricao();
        List<Inscricao> listaInscricao = List.of(inscricao);

        when(inscricaoRepository.findByTreinamentoIdAndStatus(any(Long.class),eq(StatusInscricao.CONFIRMADO))).thenReturn(listaInscricao);
        //Execução
        List<Colaborador> listaColaboradoresInscritos = capacitaColaboradorService.consultaColaboradoresInscricoesAtivas(1L);
        //Validação
        assertNotNull(listaColaboradoresInscritos);
        assertFalse(listaColaboradoresInscritos.isEmpty());
    }
    @Test
    public void consultaNenhumColaboradorInscritoEmUmTreinamento(){
        //Preparação
        when(inscricaoRepository.findByTreinamentoIdAndStatus(any(Long.class),eq(StatusInscricao.CONFIRMADO))).thenReturn(Collections.emptyList());
        //Execução
        List<Colaborador> colaboradores = capacitaColaboradorService.consultaColaboradoresInscricoesAtivas(1L);
        // Validação
        assertNotNull(colaboradores);
        assertTrue(colaboradores.isEmpty());

    }


    private Colaborador instanciarColaborador(){
        Colaborador colaborador = new Colaborador("Alan","alan@hotmail.com","desenvolvedor");
        return colaborador;
    }
    private Treinamento instanciarTreinamento(){
        Treinamento treinamento = new Treinamento("Desenvolvimento de uma applicação","Fazer API", StatusTreinamento.FINALIZADO);
        return treinamento;
    }
    private Inscricao instancearInscricao(){
        Inscricao inscricao = new Inscricao(Date.from(Instant.now()), StatusInscricao.CONFIRMADO,instanciarColaborador(),instanciarTreinamento());
        return inscricao;
    }


}
