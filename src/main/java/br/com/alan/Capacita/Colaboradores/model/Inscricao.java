package br.com.alan.Capacita.Colaboradores.model;

import br.com.alan.Capacita.Colaboradores.enums.StatusInscricao;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;


@Table(name = "tb_inscricao")
@Entity
@NoArgsConstructor
@Data
public class Inscricao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date data;
    @Enumerated(EnumType.STRING)
    private StatusInscricao status;
    @ManyToOne
    private Colaborador colaborador;
    @ManyToOne
    private Treinamento treinamento;

    public Inscricao(Date data, StatusInscricao status, Colaborador colaborador, Treinamento treinamento) {
        this.data = data;
        this.status = status;
        this.colaborador = colaborador;
        this.treinamento = treinamento;
    }

}
