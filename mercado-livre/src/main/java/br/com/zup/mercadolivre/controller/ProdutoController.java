package br.com.zup.mercadolivre.controller;


import br.com.zup.mercadolivre.modelo.Caracteristica;
import br.com.zup.mercadolivre.modelo.Categoria;
import br.com.zup.mercadolivre.modelo.DTO.ProdutoForm;
import br.com.zup.mercadolivre.modelo.Produto;
import br.com.zup.mercadolivre.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private EntityManager em;

    @Autowired
    private  ProdutoRepository produtoRepository;


    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid ProdutoForm produtoForm){
        Categoria categoriaProduto = em.find(Categoria.class, produtoForm.getIdCategoria());
        if (categoriaProduto == null) return ResponseEntity.badRequest().build();
        List<Caracteristica> caracteristicas = new ArrayList<>();
        for (Long idCarac: produtoForm.getIdsCaracteristicas()) {
            Caracteristica novaCaracteristica = em.find(Caracteristica.class,idCarac);
            if (novaCaracteristica == null) return ResponseEntity.badRequest().build();
            caracteristicas.add(novaCaracteristica);
        }
        Produto novoProduto = new Produto(produtoForm.getNome(),produtoForm.getValor(),produtoForm.getQuantidade(),
                                            caracteristicas,produtoForm.getDescricao(),categoriaProduto);
        produtoRepository.save(novoProduto);
        return ResponseEntity.ok().build();
    }

}
