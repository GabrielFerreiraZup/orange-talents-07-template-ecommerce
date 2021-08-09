package br.com.zup.mercadolivre.config.security.controller;


import br.com.zup.mercadolivre.config.security.modelo.AutentificacaoForm;
import br.com.zup.mercadolivre.config.security.modelo.TokenDto;
import br.com.zup.mercadolivre.config.security.service.TokenService;
import br.com.zup.mercadolivre.modelo.Usuario;
import br.com.zup.mercadolivre.repository.UsuarioRepository;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    private  AuthenticationManager authenticationManager;
    @Autowired
    public AutenticacaoController(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid AutentificacaoForm autentificacaoForm){
        System.out.print(autentificacaoForm.getLogin()+"\n");
        System.out.print(autentificacaoForm.getSenha()+"\n");
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken
                (autentificacaoForm.getLogin(),autentificacaoForm.getSenha());
        try {
            Authentication authentication = authenticationManager.authenticate(login);
            String token = tokenService.gerarToken(authentication);
            //System.out.print(token);
            return ResponseEntity.ok(new TokenDto(token,"Bearer"));

        }
        catch(AuthenticationException e){
            return ResponseEntity.badRequest().build();
        }
    }

}
