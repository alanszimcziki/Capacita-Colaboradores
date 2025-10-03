package br.com.alan.Capacita.Colaboradores.controller.exception;

import br.com.alan.Capacita.Colaboradores.exceptions.ColaboradorJaInscritoNoTreinamentoException;
import br.com.alan.Capacita.Colaboradores.exceptions.ColaboradorNaoEncontradoException;
import br.com.alan.Capacita.Colaboradores.exceptions.InscricaoNaoEncontradaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GerenciadorDeExcessoes  {

    private static final Logger logger = LoggerFactory.getLogger(GerenciadorDeExcessoes.class);



    @ExceptionHandler({ColaboradorNaoEncontradoException.class, InscricaoNaoEncontradaException.class, ColaboradorJaInscritoNoTreinamentoException.class})
    public ResponseEntity<String> colaboradorNaoEncontrado(ColaboradorNaoEncontradoException e){
        logger.warn(e.getMessage(),e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> trataDadosInvalidos(MethodArgumentNotValidException e){
        List<String> erros = new ArrayList<>();
        e.getBindingResult().getFieldErrors().forEach(erro ->
                erros.add(erro.getDefaultMessage()));
        logger.warn("Dados inválidos: " + erros);
        return ResponseEntity.badRequest().body(erros);
    }

    //todo: Tratar Excessões de Colaborador e Treinamentos não encontrados, retornar NOT FOUND

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> trataException(Exception e){
        logger.error("Ocorreu um erro com a solicitação: " + e.getMessage(),e);
        return ResponseEntity.internalServerError().body(
                "Impossível de fazer requisição, entre em contato com suporte!");
    }




}
