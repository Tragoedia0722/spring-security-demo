package com.example.spring_security_demo;

import com.example.spring_security_demo.util.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringSecurityDemoApplicationTests {

    @Test
    void jwt_test() throws Exception {
        String test = JwtUtils.createJWT("test");
        System.out.println(test);
        Claims claims = JwtUtils.parseJWT(test);
        String subject = claims.getSubject();
        System.out.println(subject);

    }

}
