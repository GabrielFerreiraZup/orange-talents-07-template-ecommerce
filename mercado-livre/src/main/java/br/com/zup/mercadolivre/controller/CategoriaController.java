package br.com.zup.mercadolivre.controller;


import br.com.zup.mercadolivre.modelo.Categoria;
import br.com.zup.mercadolivre.modelo.DTO.CategoriaForm;
import br.com.zup.mercadolivre.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    CategoriaRepository categoriaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CategoriaForm categoriaForm){
        if (categoriaForm.getIdPai() != null){
            Optional<Categoria> categoriaMae = categoriaRepository.findById(categoriaForm.getIdPai());
            if(categoriaMae.isEmpty()){
                return ResponseEntity.badRequest().build();
            }
            categoriaRepository.save(new Categoria(categoriaForm.getNome(),categoriaMae.get()));
            return ResponseEntity.ok().build();
        }
        categoriaRepository.save(new Categoria(categoriaForm.getNome()));
        return ResponseEntity.ok().build();
    }

}
