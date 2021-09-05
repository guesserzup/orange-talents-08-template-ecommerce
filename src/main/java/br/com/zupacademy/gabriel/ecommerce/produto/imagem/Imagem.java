package br.com.zupacademy.gabriel.ecommerce.produto.imagem;

import br.com.zupacademy.gabriel.ecommerce.produto.Produto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String linkImagem;

    @ManyToOne
    @NotNull
    private Produto produto;

    @Deprecated
    public Imagem() {
    }

    public Imagem(String link, Produto produto) {
        this.linkImagem = link;
        this.produto = produto;
    }

    public String getLinkImagem() {
        return linkImagem;
    }
}
