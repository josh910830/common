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
        SecurityProperties securityProperties = new SecurityProperties();
        securityProperties.setSecret("secret");
        securityProperties.setExpMin(30);

        jwtFactory = new JwtFactory(securityProperties);
        jwtReader = new JwtReader(securityProperties);

        Long id = 1L;

        String jwt = jwtFactory.create(id);
        String audience = jwtReader.getAudience(jwt);

        assertThat(audience).isEqualTo(id.toString());
    }

    @Test
    @DisplayName("만료 - 예외")
    void onExpired() {
        SecurityProperties securityProperties = new SecurityProperties();
        securityProperties.setSecret("secret");
        securityProperties.setExpMin(0);

        jwtFactory = new JwtFactory(securityProperties);
        jwtReader = new JwtReader(securityProperties);

        Long id = 1L;

        String jwt = jwtFactory.create(id);
        Executable reading = () -> jwtReader.getAudience(jwt);

        String message = assertThrows(InvalidJwtException.class, reading).getMessage();
        assertThat(message).isEqualTo("Expired Jwt");
    }

    @Test
    @DisplayName("서명 - 예외")
    void onInvalid() {
        SecurityProperties prop1 = new SecurityProperties();
        prop1.setSecret("secret1");
        prop1.setExpMin(30);
        jwtFactory = new JwtFactory(prop1);

        SecurityProperties prop2 = new SecurityProperties();
        prop2.setSecret("secret2");
        prop2.setExpMin(30);
        jwtReader = new JwtReader(prop2);

        Long id = 1L;

        String jwt = jwtFactory.create(id);
        Executable reading = () -> jwtReader.getAudience(jwt);

        String message = assertThrows(InvalidJwtException.class, reading).getMessage();
        assertThat(message).isEqualTo("Invalid Signature");
    }

    @Test
    @DisplayName("형식 - 예외")
    void onMalformed() {
        String jwt = "malformed";

        SecurityProperties securityProperties = new SecurityProperties();
        securityProperties.setSecret("secret");
        securityProperties.setExpMin(30);
        jwtReader = new JwtReader(securityProperties);

        Executable reading = () -> jwtReader.getAudience(jwt);

        String message = assertThrows(InvalidJwtException.class, reading).getMessage();
        assertThat(message).isEqualTo("Malformed Jwt");
    }

}
