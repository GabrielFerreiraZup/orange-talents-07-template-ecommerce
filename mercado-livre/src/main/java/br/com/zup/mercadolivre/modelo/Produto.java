package br.com.zup.mercadolivre.modelo;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @ManyToMany
    @JoinTable(
            name = "produto_caracteristica",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "caracteristica_id"))
    private List<Caracteristica> caracteristicas;
    @NotNull
    private String descricao;
    @ManyToOne
    private Categoria categoria;
    @NotNull
    private LocalDateTime horaCadastro;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private List<ImagemProduto> imagens = new ArrayList<>();
    @ManyToOne
    private Usuario usuario;



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


    public List<ImagemProduto> getImagens() {
        return imagens;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void linkImages(List<String> links){
        List<ImagemProduto> listaImagemProduto = new ArrayList<>();
        for (String link:links) {
            ImagemProduto novaImagemProduto = new ImagemProduto(this,link);
            listaImagemProduto.add(novaImagemProduto);
        }
        this.imagens.addAll(listaImagemProduto);
    }


    @Deprecated
    public Produto() {
    }

    public Produto(String nome, Double valor, Integer quantidade, List<Caracteristica> caracteristicas, String descricao, Categoria categoria, Usuario usuario) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoria = categoria;
        this.horaCadastro = horaCadastro;
        this.horaCadastro = LocalDateTime.now();
        this.usuario = usuario;
    }


    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", caracteristicas=" + caracteristicas +
                ", descricao='" + descricao + '\'' +
                ", categoria=" + categoria +
                ", horaCadastro=" + horaCadastro +
                ", imagens=" + imagens +
                '}';
    }
}
