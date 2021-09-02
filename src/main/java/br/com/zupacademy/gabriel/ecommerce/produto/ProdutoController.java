package br.com.zupacademy.gabriel.ecommerce.produto;

import br.com.zupacademy.gabriel.ecommerce.categoria.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    CategoriaRepository categoriaRepositorio;

    @PostMapping
    @Transactional
    public ProdutoDto adicionar(@RequestBody @Valid ProdutoForm form) {
        Produto produto = form.paraProduto(categoriaRepositorio);
        produtoRepository.save(produto);

        return new ProdutoDto(produto);
    }
}