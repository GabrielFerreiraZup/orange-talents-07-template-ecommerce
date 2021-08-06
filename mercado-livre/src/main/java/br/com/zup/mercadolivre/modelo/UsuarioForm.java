package br.com.zup.mercadolivre.modelo;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class UsuarioForm {

    @NotBlank
    @Email
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
