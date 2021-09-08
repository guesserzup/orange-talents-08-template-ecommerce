package br.com.zupacademy.gabriel.ecommerce.checkout;

import br.com.zupacademy.gabriel.ecommerce.compra.Compra;
import br.com.zupacademy.gabriel.ecommerce.validacao.ExistEntity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PaypalForm {


    @NotNull
    @ExistEntity(domainClass = Compra.class, fieldName = "id")
    private Long idCompra;

    @NotBlank
    private String idPagamento;

    @Min(0)
    @Max(1)
    @NotNull
    private Integer status;

    public PaypalForm(Long idCompra, String idPagamento, Integer status) {
        this.idCompra = idCompra;
        this.idPagamento = idPagamento;
        this.status = status;
    }

    public String processa(ProcessaTransacao processaTransacao) {

        if (this.status == 1) {
            processaTransacao.sucesso(this.idCompra, this.idPagamento);
            return "Pagamento processado com sucesso";
        }

        processaTransacao.falha(this.idCompra, this.idPagamento);
        return "Falha no pagamento";
    }
}
