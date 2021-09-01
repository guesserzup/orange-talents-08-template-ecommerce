package br.com.zupacademy.gabriel.ecommerce.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    CategoriaRepository categoriaRepository;

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaDto cadastrar(@RequestBody @Valid CategoriaForm form) {
        Categoria categoria = form.toCategoria(categoriaRepository);
        categoriaRepository.save(categoria);

        return new CategoriaDto(categoria.getId(), categoria.getNome(), categoria.getIdCategoriaMae());
    }
}
