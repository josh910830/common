package com.github.suloginscene.jwt;

import com.github.suloginscene.property.SecurityProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DisplayName("JWT 컴포넌트 테스트")
public class JwtComponentTest {

    JwtFactory jwtFactory;
    JwtReader jwtReader;


    @Test
    @DisplayName("정상")
    void onSuccess() throws InvalidJwtException {
        SecurityProperties secProp = securityProperties("secret", 30);
        jwtFactory = new JwtFactory(secProp);
        jwtReader = new JwtReader(secProp);

        String jwt = jwtFactory.create(1L);
        String audience = jwtReader.getAudience(jwt);

        assertThat(audience).isEqualTo("1");
    }

    @Test
    @DisplayName("만료 - 예외")
    void onExpired() {
        SecurityProperties secProp = securityProperties("secret", 0);
        jwtFactory = new JwtFactory(secProp);
        jwtReader = new JwtReader(secProp);

        String jwt = jwtFactory.create(1L);
        Executable reading = () -> jwtReader.getAudience(jwt);

        String message = assertThrows(InvalidJwtException.class, reading).getMessage();
        assertThat(message).isEqualTo("Expired Jwt");
    }

    @Test
    @DisplayName("서명 - 예외")
    void onInvalid() {
        jwtFactory = new JwtFactory(securityProperties("secret1", 30));
        jwtReader = new JwtReader(securityProperties("secret2", 30));

        String jwt = jwtFactory.create(1L);
        Executable reading = () -> jwtReader.getAudience(jwt);

        String message = assertThrows(InvalidJwtException.class, reading).getMessage();
        assertThat(message).isEqualTo("Invalid Signature");
    }

    @Test
    @DisplayName("형식 - 예외")
    void onMalformed() {
        jwtReader = new JwtReader(securityProperties("secret", 30));

        String jwt = "malformed";
        Executable reading = () -> jwtReader.getAudience(jwt);

        String message = assertThrows(InvalidJwtException.class, reading).getMessage();
        assertThat(message).isEqualTo("Malformed Jwt");
    }


    private SecurityProperties securityProperties(String secret, int expMin) {
        SecurityProperties securityProperties = new SecurityProperties();
        securityProperties.setSecret(secret);
        securityProperties.setExpMin(expMin);
        return securityProperties;
    }

}
