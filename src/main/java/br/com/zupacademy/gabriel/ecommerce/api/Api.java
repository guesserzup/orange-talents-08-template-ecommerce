package br.com.zupacademy.gabriel.ecommerce.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Api {

    @PostMapping("/api/v2/fiscal")
    public String apiFiscal(@RequestBody FiscalForm form) {
        return form.toString();
    }

    @PostMapping("/api/v2/ranking")
    public String apiRanking(@RequestBody RankingForm form) {
        return form.toString();
    }

}
