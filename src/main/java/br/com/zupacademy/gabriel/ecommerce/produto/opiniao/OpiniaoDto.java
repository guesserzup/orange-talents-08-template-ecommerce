package br.com.zupacademy.gabriel.ecommerce.produto.opiniao;

public class OpiniaoDto {

    private Long id;
    private Integer nota;
    private String titulo;
    private String descricao;
    private String usuario;
    private String produto;

    public OpiniaoDto(Opiniao opiniao)  {
        this.id = opiniao.getId();;
        this.nota = opiniao.getNota();
        this.titulo = opiniao.getTitulo();
        this.descricao = opiniao.getDescricao();
        this.usuario = opiniao.getUsuario().getLogin();
        this.produto = opiniao.getProduto().getNome();
    }


    public Long getId() {
        return id;
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getProduto() {
        return produto;
    }
}