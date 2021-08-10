package br.com.zup.mercadolivre.controller;


import br.com.zup.mercadolivre.modelo.Caracteristica;
import br.com.zup.mercadolivre.modelo.DTO.CaracteristicaForm;
import br.com.zup.mercadolivre.repository.CaracteristicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/caracteristicas")
public class CaracteristicaController {

    @Autowired
    private CaracteristicaRepository caracteristicaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CaracteristicaForm caracteristicaForm){
        Caracteristica caracteristicaNova = new Caracteristica(caracteristicaForm.getNome(),caracteristicaForm.getDescricao());

        caracteristicaRepository.save(caracteristicaNova);

        return ResponseEntity.ok().build();
    }

}
