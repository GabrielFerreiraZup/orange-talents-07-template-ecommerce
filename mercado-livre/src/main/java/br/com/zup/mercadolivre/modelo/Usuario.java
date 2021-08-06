package br.com.zup.mercadolivre.modelo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String login;
    @NotNull
    private String senha;
    @NotNull
    private LocalDateTime horaCadastro;

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDateTime getHoraCadastro() {
        return horaCadastro;
    }

    @Deprecated
    public Usuario(){}

    public Usuario(String login, String senha){
        this.login = login;
        this.senha = new BCryptPasswordEncoder().encode(senha);
        this.horaCadastro = LocalDateTime.now();
    }

}
