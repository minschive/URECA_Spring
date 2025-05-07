package com.mycom.myapp.jwt;

import com.mycom.myapp.config.MyUserDetailsService;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

// jwt 생성, 검증 ...

@Component
@RequiredArgsConstructor
@Getter
public class JwtUtil {

    @Value("${myapp.jwt.secret}")
    private String secretKeyStr;
    private SecretKey secretKey;
    private final long tokenValidDuration = 1000L * 60 * 60 * 24; // 하루

    private final MyUserDetailsService myUserDetailsService;

    @PostConstruct
    private void init() {
//        System.out.println(secretKeyStr);
        secretKey = new SecretKeySpec(
                secretKeyStr.getBytes(StandardCharsets.UTF_8),
                Jwts.SIG.HS256.key().build().getAlgorithm()
                );
//        System.out.println(secretKey);
    }

    // jwt 생성
    // username (subject), role
    public String createToken(String username, List<String> roles) {
        // 발급일자, 만료일자
        Date now = new Date();

        return Jwts.builder()
                .subject(username)
                .claim("roles", roles)
                .issuedAt(now) // 발급일자
                .expiration(new Date(now.getTime() + tokenValidDuration))
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();
    }

    // UserDetailsService 를 통해 UserDetails 객체를 얻고
    // 이를 통해서 UsernamePasswordAuthenticationToken 객체를 만들어 리턴
    // 유효성 검증을 아래 메소드를 통해서 DB 를 통한 검증을 진행하는 건 token 발급 기간이 길면 발급시점의 UserDetails 와 현재 UserDetails 가 다를 수 있다는 점 강조
    // 반대로 client 가 접속할 때마다, DB Access 를 해야한다. <= 이건 큰 부담
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(this.getUsernameFromToken(token));
        return new UsernamePasswordAuthenticationToken( userDetails.getUsername(), "", userDetails.getAuthorities() );
    }

    // jwt 로부터 username 추출
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token).getPayload()
                .getSubject();
    }

    // request 로부터 token 획득
    // client 가 header 에 "X-AUTH-TOKEN"
    public String getTokenFromHeader(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    // jwt 유효성 검증
    // 만료 일자만 검증
    public boolean validateToken(String token) {
        return ! Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token).getPayload()
            .getExpiration().before(new Date());
    }
}
