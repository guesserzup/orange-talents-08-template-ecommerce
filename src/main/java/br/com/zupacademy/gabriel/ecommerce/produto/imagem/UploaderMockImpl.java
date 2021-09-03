package br.com.zupacademy.gabriel.ecommerce.produto.imagem;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Component
public class UploaderMockImpl implements UploaderMockInterface {

    @Override
    public List<String> upload(List<MultipartFile> imagens) {
        return imagens.stream().map(multipartFile -> "http://" + multipartFile.getOriginalFilename()).collect(Collectors.toList());
    }

}
