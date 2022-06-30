package org.example.web.api;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.Services.Models.JWTUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AuthenticationController {

    @PostMapping("/api/login")
    public JWTUser login(@RequestParam("nickname") String nickname, @RequestParam("password") String password_hash) {

        String token = getJWTToken(nickname);
        JWTUser jWTUser = new JWTUser(nickname, token);
        return jWTUser;
    }
    private String getJWTToken(String nickname) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("PERSONAL_POSITIONS");

        String token = Jwts
                .builder()
                .setId("softTekJWT")
                .setSubject(nickname)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
