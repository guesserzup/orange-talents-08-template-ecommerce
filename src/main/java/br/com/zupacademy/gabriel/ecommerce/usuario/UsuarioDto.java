package br.com.zupacademy.gabriel.ecommerce.usuario;

import java.time.LocalDateTime;

public class UsuarioDto {

    private Long id;
    private String login;
    private LocalDateTime momentoDaCriacao;

    public UsuarioDto(Long id, String login, LocalDateTime momentoDaCriacao) {
        this.id = id;
        this.login = login;
        this.momentoDaCriacao = momentoDaCriacao;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public LocalDateTime getMomentoDaCriacao() {
        return momentoDaCriacao;
    }

}
