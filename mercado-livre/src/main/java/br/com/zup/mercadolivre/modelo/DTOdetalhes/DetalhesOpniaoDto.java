package br.com.zup.mercadolivre.modelo.DTOdetalhes;

import br.com.zup.mercadolivre.modelo.Opniao;

public class DetalhesOpniaoDto {

    private int nota;

    private String titulo;

    private String descricao;

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public DetalhesOpniaoDto(Opniao opniao) {
        this.nota = opniao.getNota();
        this.titulo = opniao.getTitulo();
        this.descricao = opniao.getDescricao();
    }
}
