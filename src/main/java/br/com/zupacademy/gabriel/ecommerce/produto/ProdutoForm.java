package br.com.zupacademy.gabriel.ecommerce.produto;

import br.com.zupacademy.gabriel.ecommerce.categoria.Categoria;
import br.com.zupacademy.gabriel.ecommerce.categoria.CategoriaRepository;
import br.com.zupacademy.gabriel.ecommerce.produto.caracteristica.CaracteristicasProdutoForm;
import br.com.zupacademy.gabriel.ecommerce.validacao.ExistEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

public class ProdutoForm {

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @PositiveOrZero
    private Integer qtdeDisponivel;

    @NotBlank
    @Length(max = 1000)
    private String descricao;


    @NotNull
    @ExistEntity(fieldName = "id", domainClass = Categoria.class)
    private Long idCategoria;

    @Size(min = 3)
    private List<CaracteristicasProdutoForm> caracteristicas;

    public ProdutoForm(String nome, BigDecimal valor, Integer qtdeDisponivel, String descricao, Long idCategoria,
                       List<CaracteristicasProdutoForm> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.qtdeDisponivel = qtdeDisponivel;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
        this.caracteristicas = caracteristicas;
    }

    public Produto paraProduto(CategoriaRepository categoriaRepositorio) {
        Categoria categoria = categoriaRepositorio.findById(idCategoria).get();
        Produto produto = new Produto(nome, valor, qtdeDisponivel, descricao, categoria);

        caracteristicas.forEach(caracteristica -> produto.adicionaCaracteristica(caracteristica.toEntity()));

        return produto;
    }
}
