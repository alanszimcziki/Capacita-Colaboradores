package br.com.alan.Capacita.Colaboradores.repository;

import br.com.alan.Capacita.Colaboradores.enums.StatusInscricao;
import br.com.alan.Capacita.Colaboradores.model.Colaborador;
import br.com.alan.Capacita.Colaboradores.model.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {
    List<Inscricao> findByTreinamentoIdAndStatus(Long idTreinamento, StatusInscricao status);
    Optional<Inscricao> findByTreinamentoIdAndColaboradorIdAndStatus(Long idTreinamento, Long idColaborador, StatusInscricao status);

}
