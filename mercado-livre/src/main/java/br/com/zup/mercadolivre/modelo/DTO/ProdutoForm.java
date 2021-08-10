package br.com.zup.mercadolivre.modelo.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

public class ProdutoForm {

    @NotBlank
    private String nome;
    @NotNull
    @Positive
    private Double valor;
    @Positive
    @NotNull
    private Integer quantidade;
    @NotNull
    @Size(min = 3)
    private List<Long> idsCaracteristicas;
    @NotBlank
    @Size(max = 1000)
    private String descricao;
    @NotNull
    private Long idCategoria;

    public String getNome() {
        return nome;
    }

    public Double getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public List<Long> getIdsCaracteristicas() {
        return idsCaracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public ProdutoForm(String nome, Double valor, Integer quantidade, List<Long> idsCaracteristicas, String descricao, Long idCategoria) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.idsCaracteristicas = idsCaracteristicas;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
    }
}
