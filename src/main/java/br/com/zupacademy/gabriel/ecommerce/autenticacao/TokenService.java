package br.com.zupacademy.gabriel.ecommerce.autenticacao;

import br.com.zupacademy.gabriel.ecommerce.usuario.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${login.jwt.expiration}")
    private String exp;

    @Value("${login.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication aut) {
        Usuario usuarioLogado = (Usuario) aut.getPrincipal();
        Date hoje = new Date();

        Date dataExp = new Date(hoje.getTime() + Long.parseLong(exp));

        return Jwts.builder().setIssuer("API Mercado Livre").setSubject(usuarioLogado.getId().toString()).setIssuedAt(hoje).setExpiration(dataExp).signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getIdUsuario(String token) {
        Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(body.getSubject());
    }

}