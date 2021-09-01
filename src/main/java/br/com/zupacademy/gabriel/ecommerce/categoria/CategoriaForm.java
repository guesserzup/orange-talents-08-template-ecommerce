package br.com.zupacademy.gabriel.ecommerce.categoria;

import br.com.zupacademy.gabriel.ecommerce.validacao.ExistEntity;
import br.com.zupacademy.gabriel.ecommerce.validacao.UniqueValue;

import javax.validation.constraints.NotBlank;

public class CategoriaForm {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    @ExistEntity(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoriaMae;

    public Categoria toCategoria(CategoriaRepository categoriaRepository) {
        Categoria categoria = new Categoria(this.nome);

        if (idCategoriaMae != null) {
            categoria.setCategoriaMae(categoriaRepository.findById(idCategoriaMae).orElse(categoria));
        }

        return categoria;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdCategoriaMae(Long idCategoriaMae) {
        this.idCategoriaMae = idCategoriaMae;

    }
}
