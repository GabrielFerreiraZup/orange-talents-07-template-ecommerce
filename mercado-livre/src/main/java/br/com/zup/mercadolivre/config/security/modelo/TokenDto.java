package br.com.zup.mercadolivre.config.security.modelo;

public class TokenDto {

    private String token;

    private String tipo;

    public String getToken() {
        return token;
    }

    public String getTipo() {
        return tipo;
    }

    public TokenDto(String token, String tipo) {
        this.token = token;
        this.tipo = tipo;
    }
}
