package br.com.zup.mercadolivre.modelo.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CaracteristicaForm {

    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public CaracteristicaForm(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }
}
