package br.com.zupacademy.gabriel.ecommerce.categoria;

public class CategoriaDto {

    private Long id;
    private String nome;
    private Long idCategoriaMae;

    public CategoriaDto(Long id, String nome, Long idCategoriaMae) {
        this.id = id;
        this.nome = nome;
        this.idCategoriaMae = idCategoriaMae;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdCategoriaMae() {
        return idCategoriaMae;
    }

}
