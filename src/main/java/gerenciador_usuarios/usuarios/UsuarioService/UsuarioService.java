package gerenciador_usuarios.usuarios.UsuarioService;

import gerenciador_usuarios.usuarios.Entity.UsuarioEntity;
import gerenciador_usuarios.usuarios.UsuarioRepository.UsuarioRepository;
import gerenciador_usuarios.usuarios.UsuarioRequestDTO.UsuarioRequestDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioEntity criar(UsuarioRequestDTO usuarioRequestDTO) {

        if (usuarioRepository.findByEmail(usuarioRequestDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNome(usuarioRequestDTO.getNome());
        usuarioEntity.setEmail(usuarioRequestDTO.getEmail());
        usuarioEntity.setSenha(passwordEncoder.encode(usuarioRequestDTO.getSenha()));
        return usuarioRepository.save(usuarioEntity);
    }

    public List<UsuarioEntity> listar() {
        return usuarioRepository.findAll();
    }

    public UsuarioEntity buscar(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }

    public UsuarioEntity atualizar(Long id, UsuarioRequestDTO usuarioRequestDTO) {
        UsuarioEntity usuarioEntity = buscar(id);
        usuarioEntity.setNome(usuarioRequestDTO.getNome());
        usuarioEntity.setEmail(usuarioRequestDTO.getEmail());

        if (usuarioRequestDTO.getSenha() != null) {
            usuarioEntity.setSenha(passwordEncoder.encode(usuarioRequestDTO.getSenha()));
        }
        return usuarioRepository.save(usuarioEntity);
    }
}
