package br.com.zupacademy.gabriel.ecommerce.compra;

public enum CompraGateway {

    PAGSEGURO {
        @Override
        String urlRetorno(Long id) {
            return "pagseguro.com?returnId={" + id.toString() + "}&redirectUrl={http://localhost:8080/pagseguro}";
        }
    },

    PAYPAL {
        @Override
        String urlRetorno(Long id) {
            return "paypal.com?buyerId={" + id.toString() + "}&redirectUrl={http://localhost:8080/paypal}";
        }
    };

    abstract String urlRetorno(Long id);
}
