package br.com.zup.mercadolivre.modelo.DTOdetalhes;

import br.com.zup.mercadolivre.modelo.ImagemProduto;
import br.com.zup.mercadolivre.modelo.Produto;

import java.util.ArrayList;
import java.util.List;

public class DetalhesImagemProdutoDto {

    private List<String> listaLinks;

    public List<String> getListaLinks() {
        return listaLinks;
    }

    public void setListaLinks(List<String> listaLinks) {
        this.listaLinks = listaLinks;
    }

    public DetalhesImagemProdutoDto(Produto produto) {
        List<String> listaLinks = new ArrayList<>();
        for (ImagemProduto imagem:produto.getImagens()) {
            listaLinks.add(imagem.getLink());
        }
        this.listaLinks = listaLinks;
    }
}
