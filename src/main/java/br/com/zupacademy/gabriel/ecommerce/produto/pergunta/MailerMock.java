package br.com.zupacademy.gabriel.ecommerce.produto.pergunta;

import br.com.zupacademy.gabriel.ecommerce.compra.Compra;
import org.springframework.stereotype.Component;

@Component
public class MailerMock {

    public static void send(Pergunta pergunta) {
        String remetente = pergunta.getUsuario().getLogin();
        String destinatario = pergunta.getProduto().getUsuario().getLogin();
        String texto = pergunta.getTitulo();

        System.out.println("Remetente: " + remetente + "\n" + "Destinatario: " + destinatario + "\n" + "Conteúdo: \n" + texto);
    }

    public void novaCompra(Compra compra) {

        String remetente = "sac@mercadolivre.com";

        String destinatario = compra.getProduto().getUsuario().getLogin();

        String conteudo = "Uma nova compra foi realizada para o seu produto: " + compra.getProduto().getNome() + " na" +
                " " + "quantidade de: " + compra.getQuantidade().toString() + " pelo comprador: " + compra.getUsuarioComprador().getLogin();

        System.out.println("Remetente: " + remetente + "\n" + "Destinatario: " + destinatario + "\n" + "Conteúdo: \n" + conteudo);
    }
}
