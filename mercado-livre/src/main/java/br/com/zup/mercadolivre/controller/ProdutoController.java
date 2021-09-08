package br.com.zup.mercadolivre.controller;


import br.com.zup.mercadolivre.config.security.service.TokenService;
import br.com.zup.mercadolivre.modelo.*;
import br.com.zup.mercadolivre.modelo.DTO.NovasImagensRequest;
import br.com.zup.mercadolivre.modelo.DTO.PerguntaForm;
import br.com.zup.mercadolivre.modelo.DTO.ProdutoForm;
import br.com.zup.mercadolivre.modelo.DTOdetalhes.DetalhesProdutoDto;
import br.com.zup.mercadolivre.repository.PerguntaRepository;
import br.com.zup.mercadolivre.repository.ProdutoRepository;
import br.com.zup.mercadolivre.repository.UsuarioRepository;
import br.com.zup.mercadolivre.utils.EmailSender;
import br.com.zup.mercadolivre.utils.FakeUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.parser.Entity;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private EntityManager em;

    @Autowired
    private  ProdutoRepository produtoRepository;
    @Autowired
    private FakeUploader fakeUploader;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerguntaRepository perguntaRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EmailSender emailSender;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid ProdutoForm produtoForm,HttpServletRequest request){
        Categoria categoriaProduto = em.find(Categoria.class, produtoForm.getIdCategoria());
        if (categoriaProduto == null) return ResponseEntity.badRequest().build();
        List<Caracteristica> caracteristicas = new ArrayList<>();
        for (Long idCarac: produtoForm.getIdsCaracteristicas()) {
            Caracteristica novaCaracteristica = em.find(Caracteristica.class,idCarac);
            if (novaCaracteristica == null) return ResponseEntity.badRequest().build();
            caracteristicas.add(novaCaracteristica);
        }
        String token = request.getHeader("Authorization");
        token = token.substring(7,token.length());

        Long idUsuario = tokenService.getIdToken(token);
        Usuario userProduto = usuarioRepository.findById(idUsuario).get();
        Produto novoProduto = new Produto(produtoForm.getNome(),produtoForm.getValor(),produtoForm.getQuantidade(),
                                            caracteristicas,produtoForm.getDescricao(),categoriaProduto,userProduto);
        produtoRepository.save(novoProduto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/{id}/imagens")
    public ResponseEntity cadastrarImagens(@PathVariable Long id, @Valid NovasImagensRequest request, HttpServletRequest requestHeader){
        /*

        1-> envio da foto
        2-> capturar link da foto
        3-> colocar link no produto
        4-> carregar produto
        5-> persistir produto atualizado
         */
        String token = requestHeader.getHeader("Authorization");
        token = token.substring(7,token.length());
        System.out.println(token);
        Long idUsuario = tokenService.getIdToken(token);

        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isEmpty()) return ResponseEntity.notFound().build(); //return ResponseEntity.notFound().build();
        if(produto.get().getUsuario().getId() != idUsuario) return  ResponseEntity.unprocessableEntity().build();
        List<String> links = fakeUploader.envia(request.getImagens());

        System.out.println(links);
        produto.get().linkImages(links);
        produtoRepository.save(produto.get());
        return ResponseEntity.ok().build();

    }

    @PostMapping(value = "/{id}/pergunta")
    public ResponseEntity perguntar(@PathVariable Long id, @RequestBody @Valid PerguntaForm perguntaForm,HttpServletRequest request){
        Optional<Produto> produtoPerguntado = produtoRepository.findById(id);
        if(produtoPerguntado.isEmpty()) ResponseEntity.notFound().build();
        String token = request.getHeader("Authorization");
        token = token.substring(7,token.length());
        Long idUsuario = tokenService.getIdToken(token);
        Optional<Usuario> usuarioPerguntado =usuarioRepository.findById(idUsuario);

        Pergunta novaPergunta = perguntaForm.convert(produtoPerguntado.get(),usuarioPerguntado.get());
        perguntaRepository.save(novaPergunta);
        String destinatario = produtoPerguntado.get().getUsuario().getLogin();
        System.out.println(destinatario);
        String assunto = "Nova Pergunta!";
        String corpo = "Pergunta do usuário " + usuarioPerguntado.get().getLogin() + " de título: "+ perguntaForm.getTitulo() + " foi criada agora !!";
        emailSender.sendEmail(destinatario,corpo,assunto);
        System.out.println("Enviando email agora !!!!Implementado");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesProdutoDto> detalhesProduto(@PathVariable Long id){
        Optional<Produto> produtoOpt = produtoRepository.findById(id);
        if(produtoOpt.isEmpty()) return ResponseEntity.notFound().build();
        DetalhesProdutoDto detalhesProdutoDto = new DetalhesProdutoDto(produtoOpt.get());
        return ResponseEntity.ok(detalhesProdutoDto);

    }
}
