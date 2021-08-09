package br.com.zup.mercadolivre.modelo.DTO;

import br.com.zup.mercadolivre.modelo.Usuario;
import br.com.zup.mercadolivre.validation.UniqueGeneric;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class UsuarioForm {

    @NotBlank
    @Email
    @UniqueGeneric(domainClass = Usuario.class , fieldName = "login",message = "Esse login já existe, inserir login único")
    private String login;

    @NotBlank
    @Size(min = 6)
    private String senha;




    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public UsuarioForm(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }
}
