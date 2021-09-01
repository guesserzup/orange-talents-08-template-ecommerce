package br.com.zupacademy.gabriel.ecommerce.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public UsuarioDto cadastra(@RequestBody @Valid UsuarioForm form){
        Usuario usuario = form.toEntity();
        usuarioRepository.save(usuario);

        return new UsuarioDto(usuario.getId(), usuario.getLogin(), usuario.getMomentoDaCriacao());
    }
}
