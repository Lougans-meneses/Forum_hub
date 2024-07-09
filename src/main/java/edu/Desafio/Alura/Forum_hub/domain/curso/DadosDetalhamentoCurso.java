package edu.Desafio.Alura.Forum_hub.domain.curso;

public record DadosDetalhamentoCurso(Long id, String nome, String categoria) {
    public DadosDetalhamentoCurso(Curso atualizaCurso) {
        this(atualizaCurso.getId(), atualizaCurso.getNome(), atualizaCurso.getCategoria());
    }
}
