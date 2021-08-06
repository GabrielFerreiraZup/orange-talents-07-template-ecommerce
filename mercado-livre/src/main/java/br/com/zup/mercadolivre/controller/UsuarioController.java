package br.com.zup.mercadolivre.controller;


import br.com.zup.mercadolivre.modelo.Usuario;
import br.com.zup.mercadolivre.modelo.UsuarioForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.mercadolivre.repository.UsuarioRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid UsuarioForm usuarioForm){
        Usuario novoUsuario = new Usuario(usuarioForm.getLogin(),usuarioForm.getSenha());
        usuarioRepository.save(novoUsuario);
        return ResponseEntity.ok().build();
    }
}
