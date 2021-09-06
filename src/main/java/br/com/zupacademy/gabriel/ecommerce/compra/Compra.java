package br.com.zupacademy.gabriel.ecommerce.compra;

import br.com.zupacademy.gabriel.ecommerce.produto.Produto;
import br.com.zupacademy.gabriel.ecommerce.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CompraStatus status = CompraStatus.INICIADA;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CompraGateway gatewayPagamento;

    @ManyToOne
    private Produto produto;

    @NotNull
    @Positive
    private Integer quantidade;

    @ManyToOne
    private Usuario usuarioComprador;

    @NotNull
    private BigDecimal valorProduto;

    @Deprecated
    public Compra() {
    }

    public Compra(CompraGateway gatewayPagamento, Produto produto, Integer quantidade, Usuario usuarioComprador) {
        this.gatewayPagamento = gatewayPagamento;
        this.produto = produto;
        this.quantidade = quantidade;
        this.usuarioComprador = usuarioComprador;
        this.valorProduto = produto.getValor();
    }

    public Long getId() {
        return id;
    }

    public CompraStatus getStatus() {
        return status;
    }

    public CompraGateway getGatewayPagamento() {
        return gatewayPagamento;
    }

    public Produto getProduto() {
        return produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Usuario getUsuarioComprador() {
        return usuarioComprador;
    }

    public BigDecimal getValorProduto() {
        return valorProduto;
    }

    public String getRetornoGatewayPagamento() {
        return this.gatewayPagamento.urlRetorno(this.id);
    }

}
