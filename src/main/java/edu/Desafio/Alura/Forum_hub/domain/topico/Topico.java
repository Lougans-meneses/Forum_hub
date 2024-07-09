package edu.Desafio.Alura.Forum_hub.domain.topico;

import edu.Desafio.Alura.Forum_hub.domain.curso.Curso;
import edu.Desafio.Alura.Forum_hub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String mensagem;

    private LocalDateTime data;

    private boolean status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Topico(DadosCriaTopico dadosCriaTopico) {
        this.id = dadosCriaTopico.id();
        this.titulo = dadosCriaTopico.titulo();
        this.mensagem = dadosCriaTopico.mensagem();
        this.data = dadosCriaTopico.data();
        this.status = dadosCriaTopico.status();
        this.curso = dadosCriaTopico.curso();
        this.usuario = dadosCriaTopico.usuario();
    }

    public void atualizaTopico(DadosAtualizaTopico dadosAtualizaTopico) {
        if (dadosAtualizaTopico.titulo() != null){
            this.titulo = dadosAtualizaTopico.titulo();
        }
        if (dadosAtualizaTopico.mensagem() != null){
            this.mensagem = dadosAtualizaTopico.mensagem();
        }
        if (dadosAtualizaTopico.curso() != null){
            this.curso = dadosAtualizaTopico.curso();
        }
    }
}