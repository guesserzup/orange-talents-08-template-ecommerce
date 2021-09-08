package br.com.zupacademy.gabriel.ecommerce.checkout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CheckoutController {

    @Autowired
    private ProcessaTransacao processaTransacao;

    @PostMapping("/pagseguro")
    public String pagseguro(@RequestBody @Valid PagseguroForm form){
        return form.processa(processaTransacao);
    }

    @PostMapping("/paypal")
    public String paypal(@RequestBody @Valid PaypalForm form){
        return form.processa(processaTransacao);
    }

}