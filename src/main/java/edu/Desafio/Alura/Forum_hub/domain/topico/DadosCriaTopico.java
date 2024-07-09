package edu.Desafio.Alura.Forum_hub.domain.topico;

import edu.Desafio.Alura.Forum_hub.domain.curso.Curso;
import edu.Desafio.Alura.Forum_hub.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DadosCriaTopico(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime data,
        boolean status,
        Curso curso,
        Usuario usuario) {
}