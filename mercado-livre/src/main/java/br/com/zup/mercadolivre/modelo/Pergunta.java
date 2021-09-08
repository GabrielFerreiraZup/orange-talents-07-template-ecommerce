package br.com.zup.mercadolivre.modelo;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    private LocalDateTime horaPergunta;
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private Usuario usuario;
    @Deprecated
    public Pergunta() {
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getHoraPergunta() {
        return horaPergunta;
    }

    public Pergunta(String titulo, Produto produto, Usuario usuario) {
        this.titulo = titulo;
        this.horaPergunta = LocalDateTime.now();
        this.produto = produto;
        this.usuario = usuario;
    }
}
