package br.com.zupacademy.gabriel.ecommerce.compra;

import br.com.zupacademy.gabriel.ecommerce.produto.Produto;
import br.com.zupacademy.gabriel.ecommerce.produto.ProdutoRepository;
import br.com.zupacademy.gabriel.ecommerce.usuario.Usuario;
import br.com.zupacademy.gabriel.ecommerce.validacao.RegraNegocioException;
import br.com.zupacademy.gabriel.ecommerce.validacao.UniqueValue;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraForm {

    @NotNull
    private CompraGateway compraGateway;

    @NotNull
    @UniqueValue(domainClass = Produto.class, fieldName = "id")
    private Long idProduto;

    @NotNull
    @Positive
    private Integer quantidade;

    public CompraForm(CompraGateway compraGateway, Long idProduto, Integer quantidade) {
        this.compraGateway = compraGateway;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }

    public Compra paraCompra(Usuario usuarioComprador, ProdutoRepository produtoRepository) {
        Produto produto = produtoRepository.findById(this.idProduto).get();

        if (produto.abateEstoque(quantidade)) {
            return new Compra(this.compraGateway, produto, this.quantidade, usuarioComprador);
        }

        throw new RegraNegocioException("Quantidade não disponível no estoque! \n Quantidade atual: " + produto.getQuantidade(), "quantidade", this.quantidade);
    }
}