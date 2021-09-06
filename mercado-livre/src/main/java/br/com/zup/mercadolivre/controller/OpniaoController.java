package br.com.zup.mercadolivre.controller;


import br.com.zup.mercadolivre.config.security.service.TokenService;
import br.com.zup.mercadolivre.modelo.DTO.OpniaoForm;
import br.com.zup.mercadolivre.modelo.Opniao;
import br.com.zup.mercadolivre.modelo.Produto;
import br.com.zup.mercadolivre.modelo.Usuario;
import br.com.zup.mercadolivre.repository.OpniaoRepository;
import br.com.zup.mercadolivre.repository.ProdutoRepository;
import br.com.zup.mercadolivre.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/opniao")
public class OpniaoController {

    @Autowired
    private OpniaoRepository opniaoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    UsuarioRepository  usuarioRepository;

    @PostMapping
    public ResponseEntity cadastrarOpniao(@RequestBody @Valid OpniaoForm opniaoForm, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        token = token.substring(7,token.length());
        Long idUsuario = tokenService.getIdToken(token);
        Optional<Usuario> usuarioOpnado =usuarioRepository.findById(idUsuario);
        //se fez o login essa linha nunca vai acontecer
        //if (usuarioOpnado.isEmpty()) ResponseEntity.unprocessableEntity().build();

        Optional<Produto> produtoOpinado = produtoRepository.findById(opniaoForm.getIdProduto());
        if(produtoOpinado.isEmpty()) return ResponseEntity.badRequest().build();

        Opniao novaOpniao = opniaoForm.convert(produtoOpinado.get(),usuarioOpnado.get());
        opniaoRepository.save(novaOpniao);

        return ResponseEntity.ok().build();
    }

}
