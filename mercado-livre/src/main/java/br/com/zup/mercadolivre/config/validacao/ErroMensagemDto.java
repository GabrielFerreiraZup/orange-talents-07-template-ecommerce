package br.com.zup.mercadolivre.config.validacao;

import java.time.LocalDateTime;

public class ErroMensagemDto {

    private String message;

    private String campo;

    private LocalDateTime horaErro;

    public ErroMensagemDto(String message, String campo, LocalDateTime horaErro) {
        this.message = message;
        this.campo = campo;
        this.horaErro = horaErro;
    }

    public String getMessage() {
        return message;
    }

    public String getCampo() {
        return campo;
    }

    public LocalDateTime getHoraErro() {
        return horaErro;
    }
}
