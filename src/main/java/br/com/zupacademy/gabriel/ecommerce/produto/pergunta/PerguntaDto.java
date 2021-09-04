package br.com.zupacademy.gabriel.ecommerce.produto.pergunta;

import java.time.LocalDateTime;

public class PerguntaDto {

    private String titulo;
    private String usuario;
    private String produto;
    private LocalDateTime instanteDeCriacao;

    public PerguntaDto(Pergunta pergunta) {
        this.titulo = pergunta.getTitulo();
        this.usuario = pergunta.getUsuario().getLogin();
        this.produto = pergunta.getProduto().getNome();
        this.instanteDeCriacao = pergunta.getInstanteDeCriacao();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getProduto() {
        return produto;
    }

    public LocalDateTime getInstanteDeCriacao() {
        return instanteDeCriacao;
    }
}