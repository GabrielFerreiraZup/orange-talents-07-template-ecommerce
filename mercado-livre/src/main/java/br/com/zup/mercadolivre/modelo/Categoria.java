package br.com.zup.mercadolivre.modelo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @ManyToOne
    private Categoria categoriaMae;

    @Deprecated
    public Categoria() {
    }

    public Categoria(String nome, Categoria categoriaMae) {
        this.id = id;
        this.nome = nome;
        this.categoriaMae = categoriaMae;
    }
    //construtor caso não exista mãe
    public Categoria(String nome) {
        this.id = id;
        this.nome = nome;

    }
}
