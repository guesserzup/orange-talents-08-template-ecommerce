package br.com.zupacademy.gabriel.ecommerce.validacao;

public class RegraNegocioException extends RuntimeException {

    private RetornaErro retornaErro;

    public RegraNegocioException(String mensagem, String campo,Object qtd) {
        this.retornaErro = new RetornaErro();
        retornaErro.addErrorField(campo,qtd,mensagem);
    }

    public RetornaErro getReturnError() {
        return retornaErro;
    }
}
