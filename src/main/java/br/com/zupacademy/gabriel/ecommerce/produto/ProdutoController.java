package br.com.zupacademy.gabriel.ecommerce.produto;

import br.com.zupacademy.gabriel.ecommerce.categoria.CategoriaRepository;
import br.com.zupacademy.gabriel.ecommerce.produto.detalhe.DetalhesDto;
import br.com.zupacademy.gabriel.ecommerce.produto.imagem.ImagemForm;
import br.com.zupacademy.gabriel.ecommerce.produto.imagem.Imagem;
import br.com.zupacademy.gabriel.ecommerce.produto.imagem.ImagemRepository;
import br.com.zupacademy.gabriel.ecommerce.produto.imagem.UploaderMockInterface;
import br.com.zupacademy.gabriel.ecommerce.produto.opiniao.Opiniao;
import br.com.zupacademy.gabriel.ecommerce.produto.opiniao.OpiniaoDto;
import br.com.zupacademy.gabriel.ecommerce.produto.opiniao.OpiniaoForm;
import br.com.zupacademy.gabriel.ecommerce.produto.opiniao.OpiniaoRepository;
import br.com.zupacademy.gabriel.ecommerce.produto.pergunta.*;
import br.com.zupacademy.gabriel.ecommerce.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    CategoriaRepository categoriaRepositorio;

    @Autowired
    ImagemRepository imagemRepository;

    @Autowired
    OpiniaoRepository opiniaoRepository;

    @Autowired
    PerguntaRepository perguntaRepository;

    @Autowired
    UploaderMockInterface uploaderMockInterface;

    @PostMapping
    public ProdutoDto cadastrar(@RequestBody @Valid ProdutoForm form, @AuthenticationPrincipal Usuario usuarioLogado) {
        Produto produto = form.toEntity(categoriaRepositorio, usuarioLogado);
        produtoRepository.save(produto);

        return new ProdutoDto(produto);
    }

    @Transactional
    @PostMapping("/{idProduto}/imagens")
    public String adicionarImagem(@PathVariable("idProduto") Long id, @AuthenticationPrincipal Usuario usuarioLogado,
                                  @Valid ImagemForm form) {

        Optional<Produto> produtoById = produtoRepository.findById(id);

        if (produtoById.isPresent()) {
            Produto produto = produtoById.get();
            if (produto.pertenceAoUsuario(usuarioLogado.getId())) {
                List<String> links = uploaderMockInterface.upload(form.getListaImagens());
                links.forEach(link -> imagemRepository.save(new Imagem(link, produto)));
                return "Imagens cadastradas com sucesso para o Produto: " + produto.getNome();
            }
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Deve ser alterado pelo criador do produto.");
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto ainda não registrado.");
    }

    @PostMapping("/{idProduto}/opinioes")
    @Transactional
    public OpiniaoDto adicionarOpiniao(@PathVariable("id") Long idProduto,
                                       @AuthenticationPrincipal Usuario usuarioLogado,
                                       @RequestBody @Valid OpiniaoForm form) {

        Produto produto =
                produtoRepository.findById(idProduto).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não foi encontrado"));

        Opiniao opiniao = form.toModel(produto, usuarioLogado);
        opiniaoRepository.save(opiniao);


        return new OpiniaoDto(opiniao);
    }

    @PostMapping("/{idProduto}/perguntas")
    @Transactional
    public PerguntaDto adicionarPergunta(@PathVariable("id") Long idProduto, @AuthenticationPrincipal Usuario usuario
            , @Valid @RequestBody PerguntaForm form) {

        Produto produto =
                produtoRepository.findById(idProduto).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto selecionado não existe"));

        Pergunta pergunta = form.toModel(produto, usuario);

        perguntaRepository.save(pergunta);
        MailerMock.send(pergunta);

        return new PerguntaDto(pergunta);
    }

    @GetMapping("/{id}")
    public DetalhesDto consultaDetalhes(@PathVariable("id") Long id) {

        Produto produto = produtoRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return new DetalhesDto(produto, imagemRepository, opiniaoRepository, perguntaRepository);
    }
}