package br.com.zupacademy.gabriel.ecommerce.produto.detalhe;

import br.com.zupacademy.gabriel.ecommerce.produto.Produto;
import br.com.zupacademy.gabriel.ecommerce.produto.caracteristica.CaracteristicasProduto;
import br.com.zupacademy.gabriel.ecommerce.produto.imagem.ImagemRepository;
import br.com.zupacademy.gabriel.ecommerce.produto.opiniao.Opiniao;
import br.com.zupacademy.gabriel.ecommerce.produto.opiniao.OpiniaoDto;
import br.com.zupacademy.gabriel.ecommerce.produto.opiniao.OpiniaoRepository;
import br.com.zupacademy.gabriel.ecommerce.produto.opiniao.ConsolidaOpinioes;
import br.com.zupacademy.gabriel.ecommerce.produto.pergunta.Pergunta;
import br.com.zupacademy.gabriel.ecommerce.produto.pergunta.PerguntaDto;
import br.com.zupacademy.gabriel.ecommerce.produto.pergunta.PerguntaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class DetalhesDto {

    private String nome;
    private BigDecimal preco;
    private String descricao;

    private List<String> imagens;
    private List<CaracteristicasProduto> caracteristicas;

    private List<OpiniaoDto> opinioes;
    private List<PerguntaDto> perguntas;

    private Double mediaDeNotas;
    private Integer totalDeNotas;


    public DetalhesDto(Produto produto, ImagemRepository imagemRepository, OpiniaoRepository opiniaoRepository,
                       PerguntaRepository perguntaRepository) {

        this.nome = produto.getNome();
        this.preco = produto.getValor();
        this.descricao = produto.getDescricao();
        this.imagens = produto.getLinkImagens(imagemRepository);
        this.caracteristicas = produto.getCaracteristicas();

        List<Opiniao> listaOpinioes = produto.getOpnioes(opiniaoRepository);
        ConsolidaOpinioes processadorOpinioes = new ConsolidaOpinioes(listaOpinioes);

        this.opinioes = processadorOpinioes.getOpinioesDto();
        this.mediaDeNotas = processadorOpinioes.getMediaDeNotas();
        this.totalDeNotas = processadorOpinioes.getTotalDeNotas();

        List<Pergunta> listaPerguntas = produto.getPerguntas(perguntaRepository);

        this.perguntas = listaPerguntas.stream().map(PerguntaDto::new).collect(Collectors.toList());
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<String> getImagens() {
        return imagens;
    }

    public List<CaracteristicasProduto> getCaracteristicas() {
        return caracteristicas;
    }

    public List<OpiniaoDto> getOpinioes() {
        return opinioes;
    }

    public List<PerguntaDto> getPerguntas() {
        return perguntas;
    }

    public Double getMediaDeNotas() {
        return mediaDeNotas;
    }

    public Integer getTotalDeNotas() {
        return totalDeNotas;
    }
}
