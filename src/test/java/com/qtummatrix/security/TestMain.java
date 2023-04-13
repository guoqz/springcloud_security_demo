package com.qtummatrix.security;

import com.qtummatrix.security.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

//@SpringBootTest(classes = SpringCloudSecurityApplication.class)
@SpringBootTest
public class TestMain {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testBcryptPasswordEncoder(){
        // 加密 123
        String encode = passwordEncoder.encode("123");
        String encode2 = passwordEncoder.encode("123");
        System.out.println(encode);
        System.out.println(encode2);

        // 明文相同，但密文不同，因为这种加密方式会将salt（盐）+密码明文

    }


    @Test
    public void testJwt() throws Exception {
        // 生成
        String token = JwtUtil.createJWT("123");
        System.out.println(token);

        // 解析
        Claims claims = JwtUtil.parseJWT(token);
        String userId = claims.getSubject();
        System.out.println(userId);
    }

}
