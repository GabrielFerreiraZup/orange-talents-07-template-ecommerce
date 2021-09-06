package br.com.zup.mercadolivre.modelo.DTO;

import br.com.zup.mercadolivre.modelo.Opniao;
import br.com.zup.mercadolivre.modelo.Produto;
import br.com.zup.mercadolivre.modelo.Usuario;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.*;

public class OpniaoForm {

    @NotNull
    @Min(1)
    @Max(5)
    private int nota;
    @NotBlank
    private String titulo;
    @NotBlank
    @Size(max = 500)
    private String descricao;
    @NotNull
    private Long idProduto;

    public int getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public OpniaoForm(int nota, String titulo, String descricao, Long idProduto) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.idProduto = idProduto;
    }

    public Opniao convert(Produto produto, Usuario usuario){
        return new Opniao(this.nota,this.titulo,this.descricao,produto,usuario);
    }
}
