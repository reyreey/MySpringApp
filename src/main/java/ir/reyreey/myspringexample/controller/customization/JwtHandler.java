package ir.reyreey.myspringexample.controller.customization;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 8/6/2024, Tuesday
 **/
@Component
public class JwtHandler {
    @Value("${app.security.jwt.issuer}")
    private String issuer;
    @Value("${app.security.jwt.expires-after}")
    private Long expiresAfter;
    @Value("${app.security.jwt.secret-key}")
    private String secretKey;

    public String generateToken(String username, String[] authorities) {
        return JWT.create()
                .withIssuer(issuer)
                .withSubject(username)
                .withArrayClaim("authorities",authorities)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(new Date().getTime()+expiresAfter))
                .sign(Algorithm.HMAC256(secretKey));
    }

    public DecodedJWT verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var verifier = JWT.require(algorithm).withIssuer(issuer).build();
        return verifier.verify(token);
    }
}
