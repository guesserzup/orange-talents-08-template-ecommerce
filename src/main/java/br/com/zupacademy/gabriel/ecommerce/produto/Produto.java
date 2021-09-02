package br.com.zupacademy.gabriel.ecommerce.produto;

import br.com.zupacademy.gabriel.ecommerce.categoria.Categoria;
import br.com.zupacademy.gabriel.ecommerce.produto.caracteristica.CaracteristicasProduto;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @PositiveOrZero
    private Integer quantidade;

    @NotBlank
    @Length(max = 1000)
    private String descricao;

    private LocalDateTime instanteDeCadastro = LocalDateTime.now();

    @ManyToOne
    @NotNull
    private Categoria categoria;

    @OneToMany(cascade = CascadeType.ALL)
    @Size(min = 3)
    private List<CaracteristicasProduto> caracteristicas = new ArrayList<>();

    @Deprecated
    public Produto() {
    }

    public Produto(String nome, BigDecimal valor, Integer quantidade, String descricao, Categoria categoria) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    public void adicionaCaracteristica(CaracteristicasProduto caracteristica) {
        caracteristicas.add(caracteristica);
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

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getInstanteDeCadastro() {
        return instanteDeCadastro;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public List<CaracteristicasProduto> getCaracteristicas() {
        return caracteristicas;
    }
}