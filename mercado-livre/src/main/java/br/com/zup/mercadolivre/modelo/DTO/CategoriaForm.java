package br.com.zup.mercadolivre.modelo.DTO;

import br.com.zup.mercadolivre.modelo.Categoria;
import br.com.zup.mercadolivre.validation.UniqueGeneric;

import javax.validation.constraints.NotBlank;

public class CategoriaForm {
    @NotBlank
    @UniqueGeneric(domainClass = Categoria.class, fieldName = "nome",message = "Nome da Categoria deve ser Único")
    private String nome;

    private Long idPai;



    public CategoriaForm(String nome, Long idPai) {
        this.nome = nome;
        this.idPai = idPai;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdPai() {
        return idPai;
    }
}
