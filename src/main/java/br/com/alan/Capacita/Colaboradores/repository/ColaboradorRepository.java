package br.com.alan.Capacita.Colaboradores.repository;

import br.com.alan.Capacita.Colaboradores.model.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ColaboradorRepository extends JpaRepository<Colaborador,Long> {
    Optional<Colaborador> findByEmail(String email);

}
