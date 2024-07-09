package edu.Desafio.Alura.Forum_hub.domain.curso;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private String categoria;


    public Curso(DadosCriaCurso dadoscriaCurso) {
        this.id = dadoscriaCurso.id();
        this.nome = dadoscriaCurso.nome();
        this.categoria = dadoscriaCurso.categoria();
    }

    public void atualizaCurso(DadosAtualizaCurso dados) {
        this.id = dados.id();
        this.nome = dados.nome();
        this.categoria = dados.categoria();
    }
}