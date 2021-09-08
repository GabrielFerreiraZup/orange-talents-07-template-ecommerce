package br.com.zup.mercadolivre.modelo.DTOdetalhes;

import br.com.zup.mercadolivre.modelo.Caracteristica;

public class DetalhesCaracteristicaDto {

    private String nome;

    private String descricao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public DetalhesCaracteristicaDto(Caracteristica caracteristica) {
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }
}
