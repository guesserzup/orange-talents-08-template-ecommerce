package br.com.zupacademy.gabriel.ecommerce.produto.caracteristica;

import javax.validation.constraints.NotBlank;

public class CaracteristicasProdutoForm {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @Deprecated
    public CaracteristicasProdutoForm() {
    }

    public CaracteristicasProdutoForm(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public CaracteristicasProduto toEntity() {
        return new CaracteristicasProduto(this.nome,this.descricao);
    }
}