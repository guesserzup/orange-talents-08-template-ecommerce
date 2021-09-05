package br.com.zupacademy.gabriel.ecommerce.produto.opiniao;

import java.util.List;
import java.util.stream.Collectors;

public class ConsolidaOpinioes {

    private List<OpiniaoDto> opinioesDto;
    private Double mediaDeNotas;
    private Integer totalDeNotas;

    public ConsolidaOpinioes(List<Opiniao> listaOpinioes) {
        opinioesDto = listaOpinioes.stream().map(OpiniaoDto::new).collect(Collectors.toList());
        totalDeNotas = listaOpinioes.size();
        mediaDeNotas = listaOpinioes.stream().mapToDouble(Opiniao::getNota).average().orElse(0);
    }

    public List<OpiniaoDto> getOpinioesDto() {
        return opinioesDto;
    }

    public Double getMediaDeNotas() {
        return mediaDeNotas;
    }

    public Integer getTotalDeNotas() {
        return totalDeNotas;
    }

}
