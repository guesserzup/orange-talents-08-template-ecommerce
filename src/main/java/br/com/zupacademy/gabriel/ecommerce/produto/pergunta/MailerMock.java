package br.com.zupacademy.gabriel.ecommerce.produto.pergunta;

public class MailerMock {

    public static void send(Pergunta pergunta) {
        String remetente = pergunta.getUsuario().getLogin();
        String destinatario = pergunta.getProduto().getUsuario().getLogin();
        String texto = pergunta.getTitulo();

        System.out.println("Remetente: " + remetente + "\n" + "Destinatario: " + destinatario + "\n" + "Conteúdo: \n" + texto);
    }
}
