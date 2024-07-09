package edu.Desafio.Alura.Forum_hub.controller;

import edu.Desafio.Alura.Forum_hub.domain.usuario.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public Usuario criarUsuario(@RequestBody @Valid DadosCriaUsuario dadosCriaUsuario) {

        var encripta = new BCryptPasswordEncoder();
        var senhaEncriptada = encripta.encode(dadosCriaUsuario.senha());

        var usuario = new Usuario();
        usuario.setEmail(dadosCriaUsuario.email());
        usuario.setNome(dadosCriaUsuario.nome());
        usuario.setSenha(senhaEncriptada);

        usuarioRepository.save(usuario);
        return usuario;
    }

    @GetMapping
    public ResponseEntity listagemUsuarios(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        var page = usuarioRepository.findAll();
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizaUsuario dados) {
        var atualizaUsuario = usuarioRepository.getReferenceById(dados.id());
        atualizaUsuario.atualizaUsuario(dados);

        return ResponseEntity.ok(new DadosDetalhamentoUsuario(atualizaUsuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        Optional<Usuario> usuarioExclui = usuarioRepository.findById(id);
        if (usuarioExclui.isPresent()){
            usuarioRepository.deleteById(id);
        }

        return ResponseEntity.noContent().build();
    }
}