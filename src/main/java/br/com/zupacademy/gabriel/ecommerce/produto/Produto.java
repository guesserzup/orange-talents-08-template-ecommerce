package br.com.zupacademy.gabriel.ecommerce.produto;

import br.com.zupacademy.gabriel.ecommerce.categoria.Categoria;
import br.com.zupacademy.gabriel.ecommerce.produto.caracteristica.CaracteristicasProduto;
import br.com.zupacademy.gabriel.ecommerce.produto.imagem.Imagem;
import br.com.zupacademy.gabriel.ecommerce.usuario.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    private Usuario usuario;

    @ManyToOne
    @NotNull
    private Categoria categoria;

    @OneToMany(cascade = CascadeType.ALL)
    @Size(min = 3)
    private List<CaracteristicasProduto> caracteristicas = new ArrayList<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<Imagem> imagens = new HashSet<>();

    @Deprecated
    public Produto() {
    }

    public Produto(String nome, BigDecimal valor, Integer quantidade, String descricao, Categoria categoria,
                   Usuario usuario) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuario;
    }

    public void adicionaCaracteristica(CaracteristicasProduto caracteristica) {
        caracteristicas.add(caracteristica);
    }

    public void associaImagens(Set<String> links) {
        Set<Imagem> imagens = links.stream()
                .map(link -> new Imagem(link, this))
                .collect(Collectors.toSet());

        this.imagens.addAll(imagens);
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

    public boolean pertenceAoUsuario(Long id) { return this.id == id; }

    public List<CaracteristicasProduto> getCaracteristicas() {
        return caracteristicas;
    }
}