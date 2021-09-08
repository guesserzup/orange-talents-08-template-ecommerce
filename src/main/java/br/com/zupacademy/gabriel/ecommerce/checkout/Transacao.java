package br.com.zupacademy.gabriel.ecommerce.checkout;

import br.com.zupacademy.gabriel.ecommerce.compra.Compra;
import br.com.zupacademy.gabriel.ecommerce.validacao.RegraNegocioException;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Compra compra;

    @NotBlank
    private String transacaoOrigem;

    @Enumerated(EnumType.STRING)
    @NotNull
    private StatusTransacao statusTransacao;

    private LocalDateTime instanteMovimento = LocalDateTime.now();

    @Deprecated
    public Transacao() {
    }

    public Transacao(Compra compra, String transacaoOrigem, StatusTransacao statusTransacao) {
        this.compra = compra;
        this.transacaoOrigem = transacaoOrigem;
        this.statusTransacao = statusTransacao;
    }

    public Transacao alteraStatus(Compra compra, StatusTransacao novoStatus) {

        if (!Objects.equals(this.compra.getId(), compra.getId())) {
            throw new RegraNegocioException("Transação com esse Id já existente", "idPagamento", this.transacaoOrigem);
        }

        if (this.statusTransacao == StatusTransacao.SUCESSO) {
            throw new RegraNegocioException("Transação com Status sucesso, já está finalizada", "idPagamento", this.transacaoOrigem);
        }

        this.instanteMovimento = LocalDateTime.now();
        this.statusTransacao = novoStatus;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Compra getCompra() {
        return compra;
    }

    public String getTransacaoOrigem() {
        return transacaoOrigem;
    }

    public StatusTransacao getStatusTransacao() {
        return statusTransacao;
    }

    public LocalDateTime getInstanteMovimento() {
        return instanteMovimento;
    }
}