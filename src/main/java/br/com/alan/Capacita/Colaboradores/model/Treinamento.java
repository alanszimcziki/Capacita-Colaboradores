package br.com.alan.Capacita.Colaboradores.model;

import br.com.alan.Capacita.Colaboradores.enums.StatusTreinamento;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "tb_treinamento")
@Entity
@NoArgsConstructor
@Data
public class Treinamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private StatusTreinamento status;

    public Treinamento(String titulo, String descricao, StatusTreinamento status) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
    }
}
