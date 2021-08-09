package br.com.zup.mercadolivre.config.security.service;


import br.com.zup.mercadolivre.modelo.Usuario;
import br.com.zup.mercadolivre.repository.UsuarioRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${mercado.jwt.expiration}")
    private String expiration;

    @Value("${mercado.jwt.secret}")
    private String secret;

    public Long getIdToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }


    public String gerarToken(Authentication authentication){
        Date agora = new Date();
        Date expiracao = new Date(agora.getTime()+ Long.parseLong(expiration));
        Usuario logado  = (Usuario) authentication.getPrincipal();


        return Jwts.builder()
                .setIssuer("Mercado Livre")
                .setSubject(logado.getId().toString())
                .setIssuedAt(agora)
                .setExpiration(expiracao)
                .signWith(SignatureAlgorithm.HS256,secret).compact();


    }

    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
        }
        catch(Exception e){
            return false;
        }
        return true;
        }
}
