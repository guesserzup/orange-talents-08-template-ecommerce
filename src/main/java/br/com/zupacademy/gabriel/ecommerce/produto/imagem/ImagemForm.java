package br.com.zupacademy.gabriel.ecommerce.produto.imagem;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class ImagemForm {

    @Size(min = 1)
    @NotNull
    private List<MultipartFile> listaImagens = new ArrayList<>();

    public List<MultipartFile> getListaImagens() {
        return listaImagens;
    }

    public void setListaImagens(List<MultipartFile> listaImagens) {
        this.listaImagens = listaImagens;
    }
}
