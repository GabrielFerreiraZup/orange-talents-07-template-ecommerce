package br.com.zup.mercadolivre.config.security;

import br.com.zup.mercadolivre.config.security.service.TokenService;
import br.com.zup.mercadolivre.modelo.Usuario;
import br.com.zup.mercadolivre.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AutenticacaoTokenFilter extends OncePerRequestFilter {


    private TokenService tokenService;

    private UsuarioRepository usuarioRepository;

    public AutenticacaoTokenFilter(TokenService tokenService,UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    public String getToken(HttpServletRequest request){

        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")){
            return null;
        }
        return token.substring(7,token.length());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = getToken(request);
        boolean valido = tokenService.isTokenValido(token);
        System.out.print(token+"\n");
        System.out.println(valido+"\n");
        if (valido){
            authenticarCliente(token);
        }

        filterChain.doFilter(request,response);
    }

    private void authenticarCliente(String token) {
        Long idUsuario = tokenService.getIdToken(token);
        Optional<Usuario> usuarioLogin = usuarioRepository.findById(idUsuario);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuarioLogin.get(),null,usuarioLogin.get().getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

    }
}
