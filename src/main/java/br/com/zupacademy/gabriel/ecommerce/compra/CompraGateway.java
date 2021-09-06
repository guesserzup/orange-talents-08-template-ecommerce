package br.com.zupacademy.gabriel.ecommerce.compra;

public enum CompraGateway {

    PAGSEGURO {
        @Override
        String urlRetorno(Long id) {
            return "pagseguro.com?returnId={" + id.toString() + "}&redirectUrl={urlRetornoAppPosPagamento}";
        }
    },

    PAYPAL {
        @Override
        String urlRetorno(Long id) {
            return "paypal.com?buyerId={" + id.toString() + "}&redirectUrl={urlRetornoAppPosPagamento}";
        }
    };

    abstract String urlRetorno(Long id);
}
