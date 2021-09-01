package br.com.zupacademy.gabriel.ecommerce.usuario;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioForm {

    @NotBlank
    @Email
    private String login;

    @NotBlank
    @Length(min = 6)
    private String senha;

    public UsuarioForm(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario toEntity() {
        return new Usuario(this.login,this.senha);
    }
}
