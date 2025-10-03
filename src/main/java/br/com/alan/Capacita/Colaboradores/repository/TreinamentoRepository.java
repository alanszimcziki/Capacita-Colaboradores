package br.com.alan.Capacita.Colaboradores.repository;

import br.com.alan.Capacita.Colaboradores.model.Treinamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreinamentoRepository extends JpaRepository<Treinamento,Long> {
}
