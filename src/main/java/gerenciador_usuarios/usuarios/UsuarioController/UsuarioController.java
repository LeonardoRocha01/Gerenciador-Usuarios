package gerenciador_usuarios.usuarios.UsuarioController;

import gerenciador_usuarios.usuarios.Entity.UsuarioEntity;
import gerenciador_usuarios.usuarios.UsuarioRequestDTO.UsuarioRequestDTO;
import gerenciador_usuarios.usuarios.UsuarioService.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public UsuarioEntity criar(@RequestBody @Valid UsuarioRequestDTO usuarioRequestDTO) {
        return usuarioService.criar(usuarioRequestDTO);
    }

    @GetMapping
    public List<UsuarioEntity> listar() {
        return usuarioService.listar();
    }

    @GetMapping("/{id}")
    public UsuarioEntity buscar(@PathVariable Long id) {
        return usuarioService.buscar(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        usuarioService.deletar(id);
    }

    @PutMapping("/{id}")
    public UsuarioEntity atualizar(@PathVariable Long id, @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        return usuarioService.atualizar(id, usuarioRequestDTO);
    }
}
