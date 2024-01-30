package co.jp.amazawa.training.demo.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public final class JwtHelper {

    private final String accessTokenSecret;

    private final Duration accessTokenExpire;

    private final String refreshTokenSecret;

    private final Duration refreshTokenExpire;

    private final JWTVerifier accessTokenVerifier;

    private final JWTVerifier refreshTokenVerifier;

    public JwtHelper(
            @Value("${auth.access-token.secret:my_secret1}") String accessTokenSecret,
            @Value("${auth.access-token.expire:10m}") Duration accessTokenExpire,
            @Value("${auth.refresh-token.secret:my_secret2}") String refreshTokenSecret,
            @Value("${auth.refresh-token.expire:24h}") Duration refreshTokenExpire)
    {
        this.accessTokenSecret = accessTokenSecret;
        this.accessTokenExpire = accessTokenExpire;
        this.refreshTokenSecret = refreshTokenSecret;
        this.refreshTokenExpire = refreshTokenExpire;
        this.accessTokenVerifier = JWT.require(Algorithm.HMAC256(accessTokenSecret)).build();
        this.refreshTokenVerifier = JWT.require(Algorithm.HMAC256(refreshTokenSecret)).build();
    }

    public String generateAccessToken(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create()
                .withHeader(map)
                .withClaim("id", user.getId())
                .withClaim("name", user.getName())
                .withClaim("email", user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + accessTokenExpire.toMillis()))
                .withIssuedAt(new Date())
                .sign(Algorithm.HMAC256(accessTokenSecret));
        return token;
    }

    public String generateRefreshToken(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create()
                .withHeader(map)
                .withClaim("id", user.getId())
                .withClaim("name", user.getName())
                .withClaim("email", user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + refreshTokenExpire.toMillis()))
                .withIssuedAt(new Date())
                .sign(Algorithm.HMAC256(refreshTokenSecret));
        return token;
    }

    public User verifyAccessToken(String accessToken) {
        DecodedJWT jwt = null;
        try {
            jwt = accessTokenVerifier.verify(accessToken);
        } catch (JWTVerificationException e) {
            // TODO 例外をより具体的に
            return null;
        }
        String id = jwt.getClaim("id").asString();
        String name = jwt.getClaim("name").asString();
        String email = jwt.getClaim("email").asString();
        User user = new User(id, name, email);
        return user;
    }

    public User verifyRefreshToken(String refreshToken) {
        DecodedJWT jwt = null;
        try {
            jwt = refreshTokenVerifier.verify(refreshToken);
        } catch (JWTVerificationException e) {
            // TODO 例外をより具体的に
            return null;
        }
        String id = jwt.getClaim("id").asString();
        String name = jwt.getClaim("name").asString();
        String email = jwt.getClaim("email").asString();
        User user = new User(id, name, email);
        return user;
    }

}
