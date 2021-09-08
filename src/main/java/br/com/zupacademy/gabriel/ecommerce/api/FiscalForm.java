package br.com.zupacademy.gabriel.ecommerce.api;

import br.com.zupacademy.gabriel.ecommerce.compra.Compra;

public class FiscalForm {

    private Long idUsuario;
    private Long idCompra;

    public FiscalForm(Compra compra) {
        this.idUsuario = compra.getUsuarioComprador().getId();
        this.idCompra = compra.getId();
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    @Override
    public String toString() {
        return "FiscalForm{" + "idUsuario=" + idUsuario + ", idCompra=" + idCompra + '}';
    }
}
