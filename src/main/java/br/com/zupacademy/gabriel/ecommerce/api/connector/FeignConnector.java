package br.com.zupacademy.gabriel.ecommerce.api.connector;

import br.com.zupacademy.gabriel.ecommerce.api.FiscalForm;
import br.com.zupacademy.gabriel.ecommerce.api.RankingForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "localhost:8080/api/v2/", name = "api")
public interface FeignConnector {

    @PostMapping(value = "/fiscal")
    String notificaFiscal(@RequestBody FiscalForm form);


    @PostMapping(value = "/ranking")
    String notificaRanking(@RequestBody RankingForm form);
}