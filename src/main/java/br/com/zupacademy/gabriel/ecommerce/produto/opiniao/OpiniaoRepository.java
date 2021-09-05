package br.com.zupacademy.gabriel.ecommerce.produto.opiniao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpiniaoRepository extends JpaRepository<Opiniao, Long> {
    List<Opiniao> findByProdutoId(Long id);
}
