package com.codecrafter.spring_with_flutter.base.security.jwt;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest()
@ActiveProfiles("test")
class JwtProviderTest {
    @Autowired
    JwtProvider jwtProvider;
//    @Value("${custom.jwt.secretKey}")
//    private String secretKey;

    @Test
    @DisplayName("JWT 발급")
    void createJwtTest() {
        String jwt = jwtProvider.createJwt("testUserId");
        assertThat(jwtProvider.getUserName(jwt)).isEqualTo("testUserId");
    }
}