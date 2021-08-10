package br.com.zup.mercadolivre.modelo;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nome;
    @NotNull
    @Positive
    private Double valor;
    @NotNull
    @Positive
    private Integer quantidade;
    @ManyToMany(mappedBy = "produtosCategoria")
    private List<Caracteristica> caracteristicas;
    @NotNull
    private String descricao;
    @ManyToOne
    private Categoria categoria;
    @NotNull
    private LocalDateTime horaCadastro;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public List<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public LocalDateTime getHoraCadastro() {
        return horaCadastro;
    }

    @Deprecated
    public Produto() {
    }

    public Produto(String nome, Double valor, Integer quantidade,
                   List<Caracteristica> caracteristicas, String descricao, Categoria categoria) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoria = categoria;
        this.horaCadastro = LocalDateTime.now();
    }
}
