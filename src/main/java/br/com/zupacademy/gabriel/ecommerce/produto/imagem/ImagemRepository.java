package br.com.zupacademy.gabriel.ecommerce.produto.imagem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagemRepository extends JpaRepository<Imagem, Long> {
    List<Imagem> findByProdutoId(Long id);
}
