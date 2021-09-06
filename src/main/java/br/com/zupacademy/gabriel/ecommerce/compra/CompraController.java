package br.com.zupacademy.gabriel.ecommerce.compra;

import br.com.zupacademy.gabriel.ecommerce.produto.ProdutoRepository;
import br.com.zupacademy.gabriel.ecommerce.produto.pergunta.MailerMock;
import br.com.zupacademy.gabriel.ecommerce.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    CompraRepository compraRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    MailerMock mailerMock;

    @PostMapping
    public String comprar(@AuthenticationPrincipal Usuario usuario, @RequestBody @Valid CompraForm form) {
        Compra compra = form.paraCompra(usuario, produtoRepository);
        compraRepository.save(compra);
        mailerMock.novaCompra(compra);

        return compra.getRetornoGatewayPagamento();
    }
}