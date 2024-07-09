package edu.Desafio.Alura.Forum_hub.security;

import edu.Desafio.Alura.Forum_hub.domain.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    //para injetar o repository neste filtro, para permitir uma consulta no BD
    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //varíável que recebe o token a partir de um método que extrai o token
        var tokenJWT = recuperarToken(request);
        //System.out.println(tokenJWT);
        System.out.println("FILTRO SENDO CHAMADO!!!!");

        /*
         * Valida, se existir, o token e recupera o seu subject
         */
        if (tokenJWT != null) {
            var subject = tokenService.getSubject(tokenJWT);
            var usuario = repository.findByEmail(subject);

            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("LOGADO NA REQUISIÇÃO");
        }

        //para verificar se recuperou o subject do token
        // System.out.println(subject);

        //para chamar o próximo filtro, encaminhando o request e o response
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        //se existe o cabeçalho
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}