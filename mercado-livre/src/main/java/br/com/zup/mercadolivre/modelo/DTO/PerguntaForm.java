package br.com.zup.mercadolivre.modelo.DTO;

import br.com.zup.mercadolivre.modelo.Pergunta;
import br.com.zup.mercadolivre.modelo.Produto;
import br.com.zup.mercadolivre.modelo.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.parameters.P;

import javax.validation.constraints.NotBlank;

public class PerguntaForm {
    @NotBlank
    private String titulo;

    public String getTitulo() {
        return titulo;
    }

    public PerguntaForm(@JsonProperty("titulo") String titulo) {
        this.titulo = titulo;
    }

    public Pergunta convert(Produto produto, Usuario usuario){
        return new Pergunta(this.titulo,produto,usuario);
    }

}
