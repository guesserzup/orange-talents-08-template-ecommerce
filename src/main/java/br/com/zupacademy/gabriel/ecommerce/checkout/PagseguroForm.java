package br.com.zupacademy.gabriel.ecommerce.checkout;

import br.com.zupacademy.gabriel.ecommerce.compra.Compra;
import br.com.zupacademy.gabriel.ecommerce.validacao.ExistEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class PagseguroForm {

    @NotNull
    @ExistEntity(domainClass = Compra.class, fieldName = "id")
    private Long idCompra;

    @NotBlank
    private String idPagamento;

    @NotBlank
    @Pattern(regexp = "( SUCESSO | FALHA )" )
    private String status;

    public PagseguroForm(Long idCompra, String idPagamento, String status) {
        this.idCompra = idCompra;
        this.idPagamento = idPagamento;
        this.status = status;
    }

    public String processa(ProcessaTransacao processaTransacao) {

        if (this.status.equals("SUCESSO")) {
            processaTransacao.sucesso(this.idCompra, this.idPagamento);
            return "Pagamento processado com sucesso";
        }

        processaTransacao.falha(this.idCompra, this.idPagamento);
        return "Falha no pagamento";
    }
}
