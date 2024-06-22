package br.com.alura.desafioforumalura.infra.security.authentication;

import br.com.alura.desafioforumalura.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario) throws JWTCreationException {
        var algoritmo = Algorithm.HMAC256(secret);
        return JWT.create()
                .withIssuer("API Fórum ALURA")
                .withSubject(usuario.getUsername())
                .withClaim("id", usuario.getId())
                .withExpiresAt(dataExpiracao())
                .sign(algoritmo);
    }

    public String getSubject(String tokenJWT) throws JWTVerificationException {
        var algoritmo = Algorithm.HMAC256(secret);
        return JWT.require(algoritmo)
                .withIssuer("API Fórum ALURA")
                .build()
                .verify(tokenJWT)
                .getSubject();
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}