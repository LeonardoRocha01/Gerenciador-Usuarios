package gerenciador_usuarios.usuarios.UsuarioController;

import gerenciador_usuarios.usuarios.Entity.UsuarioEntity;
import gerenciador_usuarios.usuarios.UsuarioRepository.UsuarioRepository;
import gerenciador_usuarios.usuarios.UsuarioRequestDTO.UsuarioRequestDTO;
import gerenciador_usuarios.usuarios.UsuarioService.JWTService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;
    private JWTService jwtService;

    @PostMapping("/login")
    public String login(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        UsuarioEntity user = usuarioRepository.findByEmail(usuarioRequestDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if(!passwordEncoder.matches(usuarioRequestDTO.getSenha(), user.getSenha())) {
            throw new RuntimeException("Senha inválida");
        }
        return jwtService.gerarToken(user.getEmail());

    }
}
