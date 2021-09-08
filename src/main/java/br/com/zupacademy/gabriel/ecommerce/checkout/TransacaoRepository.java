package br.com.zupacademy.gabriel.ecommerce.checkout;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransacaoRepository  extends JpaRepository<Transacao,Long> {

    List<Transacao> findAllByStatusTransacaoAndCompra_Id(StatusTransacao toString, Long idCompra);
    Transacao findByTransacaoOrigem(String idPagamento);
}