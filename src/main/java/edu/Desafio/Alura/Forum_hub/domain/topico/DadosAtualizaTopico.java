package edu.Desafio.Alura.Forum_hub.domain.topico;

import edu.Desafio.Alura.Forum_hub.domain.curso.Curso;

public record DadosAtualizaTopico(Long id,
                                  String titulo,
                                  String mensagem,
                                  boolean status,
                                  Curso curso) {
}