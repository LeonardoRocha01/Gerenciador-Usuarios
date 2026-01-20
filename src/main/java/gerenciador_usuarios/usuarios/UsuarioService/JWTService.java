package gerenciador_usuarios.usuarios.UsuarioService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    private final String SECRET_KEY = "JWT_SECRET_KEY";

    public String gerarToken(String email) {
        return Jwts.builder().subject(email)
                .issuedAt(new Date())
                .expiration(new Date((System.currentTimeMillis() + 30000)))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .compact();
    }
}
