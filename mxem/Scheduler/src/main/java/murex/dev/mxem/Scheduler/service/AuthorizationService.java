package murex.dev.mxem.Scheduler.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import murex.dev.mxem.Scheduler.security.JwtConfig;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class AuthorizationService {

    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    public AuthorizationService(SecretKey secretKey,
                                JwtConfig jwtConfig) {
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
    }

    public String getUsernameFromToken(String token){
        token = token.replace(jwtConfig.getTokenPrefix(), "");

        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);

            Claims body = claimsJws.getBody();
            String username = (String) body.get("sub");
            return username;
        } catch (JwtException e) {
            throw new IllegalStateException(String.format("Token %s cannot be trusted", token));
        }
    }

}
