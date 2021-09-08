package br.com.zupacademy.gabriel.ecommerce.checkout;

import br.com.zupacademy.gabriel.ecommerce.api.FiscalForm;
import br.com.zupacademy.gabriel.ecommerce.api.RankingForm;
import br.com.zupacademy.gabriel.ecommerce.api.connector.FeignConnector;
import br.com.zupacademy.gabriel.ecommerce.compra.Compra;
import br.com.zupacademy.gabriel.ecommerce.compra.CompraRepository;
import br.com.zupacademy.gabriel.ecommerce.produto.pergunta.MailerMock;
import br.com.zupacademy.gabriel.ecommerce.validacao.RegraNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProcessaTransacao {

    private final StatusTransacao SUCESSO = StatusTransacao.SUCESSO;
    private final StatusTransacao FALHA = StatusTransacao.FALHA;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private FeignConnector feignConnector;

    @Autowired
    private MailerMock mailerMock;

    public void sucesso(Long idCompra, String idPagamento) {

        Compra compra = compraRepository.findById(idCompra).orElseThrow(() -> new RegraNegocioException("Compra inválida ", "idCompra", idCompra));

        List<Transacao> transacaoList = transacaoRepository.findAllByStatusTransacaoAndCompra_Id(SUCESSO, idCompra);

        if (transacaoList.size() == 2) {
            throw new RegraNegocioException("Transação duplicada", "idCompra", idCompra);
        }

        Transacao transacao = retornaAtualizada(compra, idPagamento, SUCESSO);
        transacaoRepository.save(transacao);

        feignConnector.notificaFiscal(new FiscalForm(compra));
        feignConnector.notificaRanking(new RankingForm(compra.getId(), compra.getProduto().getUsuario().getId()));

        mailerMock.pagamentoSucesso(compra);
    }

    public void falha(Long idCompra, String idPagamento) {
        Compra compra = compraRepository.findById(idCompra).orElseThrow(() -> new RegraNegocioException("Esta compra "
                + "é inválida ", "idCompra", idCompra));

        Transacao transacao = retornaAtualizada(compra, idPagamento, FALHA);
        transacaoRepository.save(transacao);

        mailerMock.pagamentoFalhou(compra);
    }

    private Transacao retornaAtualizada(Compra compra, String idPagamento, StatusTransacao status) {

        Transacao transacao = transacaoRepository.findByTransacaoOrigem(idPagamento);

        if (transacao != null) {
            transacao = transacao.alteraStatus(compra, status);
        } else {
            transacao = new Transacao(compra, idPagamento, status);
        }

        return transacao;
    }
}
