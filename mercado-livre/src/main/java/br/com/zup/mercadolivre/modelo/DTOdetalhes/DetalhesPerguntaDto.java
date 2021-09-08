package br.com.zup.mercadolivre.modelo.DTOdetalhes;

import br.com.zup.mercadolivre.modelo.Pergunta;

import java.time.LocalDateTime;

public class DetalhesPerguntaDto {

    private String titulo;

    private LocalDateTime horaPergunta;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDateTime getHoraPergunta() {
        return horaPergunta;
    }

    public void setHoraPergunta(LocalDateTime horaPergunta) {
        this.horaPergunta = horaPergunta;
    }

    public DetalhesPerguntaDto(Pergunta pergunta) {
        this.titulo = pergunta.getTitulo();
        this.horaPergunta = pergunta.getHoraPergunta();
    }
}
