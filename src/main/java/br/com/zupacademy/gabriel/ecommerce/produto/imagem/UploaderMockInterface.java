package br.com.zupacademy.gabriel.ecommerce.produto.imagem;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UploaderMockInterface {
    List<String> upload(List<MultipartFile> imagens);
}
