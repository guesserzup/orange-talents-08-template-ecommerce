package br.com.zupacademy.gabriel.ecommerce.produto;

import br.com.zupacademy.gabriel.ecommerce.categoria.CategoriaRepository;
import br.com.zupacademy.gabriel.ecommerce.produto.imagem.ImagemForm;
import br.com.zupacademy.gabriel.ecommerce.produto.imagem.Imagem;
import br.com.zupacademy.gabriel.ecommerce.produto.imagem.ImagemRepository;
import br.com.zupacademy.gabriel.ecommerce.produto.imagem.UploaderMockInterface;
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
    UploaderMockInterface uploaderMockInterface;

    @Transactional
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
}