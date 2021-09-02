package br.com.zupacademy.gabriel.ecommerce.produto;

import br.com.zupacademy.gabriel.ecommerce.produto.caracteristica.CaracteristicasProduto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ProdutoDto {

    private Long id;
    private String nome;
    private BigDecimal valor;
    private Integer qtdeDisponivel;
    private String descricao;
    private LocalDateTime instanteDeCadastro;
    private Long idCategoria;
    private List<CaracteristicasProduto> caracteristicas;

    public ProdutoDto(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.qtdeDisponivel = produto.getQuantidade();
        this.descricao = produto.getDescricao();
        this.instanteDeCadastro = produto.getInstanteDeCadastro();
        this.idCategoria = produto.getCategoria().getId();
        this.caracteristicas = produto.getCaracteristicas();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQtdeDisponivel() {
        return qtdeDisponivel;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getInstanteDeCadastro() {
        return instanteDeCadastro;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public List<CaracteristicasProduto> getCaracteristicas() {
        return caracteristicas;
    }
}