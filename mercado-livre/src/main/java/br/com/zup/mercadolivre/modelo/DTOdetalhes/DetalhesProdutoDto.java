package br.com.zup.mercadolivre.modelo.DTOdetalhes;


import br.com.zup.mercadolivre.modelo.Caracteristica;
import br.com.zup.mercadolivre.modelo.Opniao;
import br.com.zup.mercadolivre.modelo.Pergunta;
import br.com.zup.mercadolivre.modelo.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class DetalhesProdutoDto {

    private String nome;

    private Double preco;

    private String descricao;

    private DetalhesImagemProdutoDto imagensProduto;

    private List<DetalhesCaracteristicaDto> caracteristicas;

    private List<DetalhesOpniaoDto> opnioes;

    private Double mediaNotas;

    private int quantidadeNotas;

    private List<DetalhesPerguntaDto> perguntas;

    //getters e setters


    public List<DetalhesPerguntaDto> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<DetalhesPerguntaDto> perguntas) {
        this.perguntas = perguntas;
    }

    public int getQuantidadeNotas() {
        return quantidadeNotas;
    }

    public void setQuantidadeNotas(int quantidadeNotas) {
        this.quantidadeNotas = quantidadeNotas;
    }

    public Double getMediaNotas() {
        return mediaNotas;
    }

    public void setMediaNotas(Double mediaNotas) {
        this.mediaNotas = mediaNotas;
    }

    public List<DetalhesOpniaoDto> getOpnioes() {
        return opnioes;
    }

    public void setOpnioes(List<DetalhesOpniaoDto> opnioes) {
        this.opnioes = opnioes;
    }

    public List<DetalhesCaracteristicaDto> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<DetalhesCaracteristicaDto> detalhesCaracteristicaDtos) {
        this.caracteristicas = detalhesCaracteristicaDtos;
    }

    public DetalhesImagemProdutoDto getImagensProduto() {
        return imagensProduto;
    }

    public void setImagensProduto(DetalhesImagemProdutoDto imagensProduto) {
        this.imagensProduto = imagensProduto;
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }
    @Deprecated
    public DetalhesProdutoDto() {
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public DetalhesProdutoDto(Produto produto) {
        this.nome = produto.getNome();
        this.preco = produto.getValor();
        this.descricao = produto.getDescricao();
        DetalhesImagemProdutoDto detalhesImagemProdutoDto = new DetalhesImagemProdutoDto(produto);
        this.imagensProduto= detalhesImagemProdutoDto;
        List<DetalhesCaracteristicaDto> listaCaracteristicas = new ArrayList<>();
        for (Caracteristica caracteristica:produto.getCaracteristicas()) {
            listaCaracteristicas.add(new DetalhesCaracteristicaDto(caracteristica));
        }
        this.caracteristicas = listaCaracteristicas;
        List<DetalhesOpniaoDto> listaOpnioes = new ArrayList<>();
        int sum = 0;

        for (Opniao opniao:produto.getOpnioes()
             ) {
            listaOpnioes.add(new DetalhesOpniaoDto(opniao));
            sum = sum +(opniao.getNota());

        }
        this.opnioes = listaOpnioes;
        if(produto.getOpnioes().size()==0){
            this.mediaNotas = 0.0;
        }
        else {
            this.mediaNotas = Double.valueOf(Double.valueOf(sum) / produto.getOpnioes().size());
        }
        this.quantidadeNotas = produto.getOpnioes().size();
        List<DetalhesPerguntaDto> perguntasList = new ArrayList<>();

        for (Pergunta pergunta:produto.getPerguntas()) {
            perguntasList.add(new DetalhesPerguntaDto(pergunta));
        }
        this.perguntas = perguntasList;

    }
}
