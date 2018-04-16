package com.guo.springboot;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.Key;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class JwtTest {

    @Test
    public void testJwt() {

        // HS256
        // 这里的key以后可以放在项目的属性文件中，全局配置即可
        String key = "kedacom";

        String payLoad = "{name:'test',sex: 'male'}";

        // subject 就是负载
        String compactJws = Jwts.builder()
                .setSubject(payLoad)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

        System.out.println(compactJws);

//        assert Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws).getBody().getSubject().equals(payLoad);
        Jws<Claims> jwtClaims = Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws);
        System.out.println(jwtClaims.getSignature());
        System.out.println(jwtClaims.getBody().getSubject());
        System.out.println(jwtClaims.getHeader().getAlgorithm());

    }

}
