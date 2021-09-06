package br.com.zup.mercadolivre.modelo;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Entity
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Produto produto;
    @NotBlank
    private String link;

    public Produto getProduto() {
        return produto;
    }

    public String getLink() {
        return link;
    }
    @Deprecated
    public ImagemProduto() {
    }

    public ImagemProduto(Produto produto, String link) {
        this.produto = produto;
        this.link = link;
    }

    @Override
    public String toString() {
        return "ImagemProduto{" +
                "id=" + id +

                ", link='" + link + '\'' +
                '}';
    }
}
