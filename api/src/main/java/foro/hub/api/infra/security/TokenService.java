package foro.hub.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import foro.hub.api.domain.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("forohub")
                    .withSubject(usuario.getNombre())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException();
        }
    }


    private Instant generarFechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }


//    public String getSubject(String token) {
//        if (token == null) {
//            throw new RuntimeException("Token is null or empty");
//        }
//        DecodedJWT verifier = null;
//        try {
//            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
//            verifier = JWT.require(algorithm)
//                    .withIssuer("foro hub")
//                    .build()
//                    .verify(token);
//
//            verifier.getSubject();
//        } catch (JWTVerificationException exception) {
//            System.out.println(exception.toString());
//        }
//        if (verifier.getSubject() == null) {
//            throw new RuntimeException("Verifier Invalido");
//        }
//        return verifier.getSubject();
//    }
public String getSubject(String token) {
    if (token == null) {
        throw new RuntimeException("Token is null or empty");
    }
    try {
        Algorithm algorithm = Algorithm.HMAC256(apiSecret);
        DecodedJWT verifier = JWT.require(algorithm)
                .withIssuer("forohub")
                .build()
                .verify(token);

        String subject = verifier.getSubject();
        if (subject == null) {
            throw new RuntimeException("Verifier Invalido");
        }
        return subject;
    } catch (JWTVerificationException exception) {
        System.out.println("Token verification failed: " + exception.toString());
        throw new RuntimeException("Invalid token", exception);
    }
}
}
