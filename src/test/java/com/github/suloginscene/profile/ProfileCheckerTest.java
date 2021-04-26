package com.github.suloginscene.profile;

import com.github.suloginscene.exception.InternalException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.springframework.core.env.Environment;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;


@DisplayName("프로파일")
class ProfileCheckerTest {

    @Test
    @DisplayName("초기화(로컬)")
    void init_local() {
        Environment env = mockEnv("local");
        Executable initializing = constructWithPostConstruct(env);
        assertDoesNotThrow(initializing);
    }

    @Test
    @DisplayName("초기화(테스트)")
    void init_test() {
        Environment env = mockEnv("test");
        Executable initializing = constructWithPostConstruct(env);
        assertDoesNotThrow(initializing);
    }

    @Test
    @DisplayName("초기화(운영)")
    void init_prod() {
        Environment env = mockEnv("prod");
        Executable initializing = constructWithPostConstruct(env);
        assertDoesNotThrow(initializing);
    }

    @Test
    @DisplayName("초기화(기타) - 예외")
    void init_etc() {
        Environment env = mockEnv("etc");
        Executable initializing = constructWithPostConstruct(env);
        assertThrows(InternalException.class, initializing);
    }

    @Test
    @DisplayName("초기화(복수) - 예외")
    void init_multi() {
        Environment env = mockEnv("local", "prod");
        Executable initializing = constructWithPostConstruct(env);
        assertThrows(InternalException.class, initializing);
    }

    @Test
    @DisplayName("테스트 확인(테스트)")
    void checkTest_true() {
        Environment env = mockEnv("test");
        ProfileChecker profileChecker = new ProfileChecker(env);
        profileChecker.readActiveProfiles();

        Executable check = profileChecker::checkTest;
        assertDoesNotThrow(check);
    }

    @Test
    @DisplayName("테스트 확인(테스트 X) - 예외")
    void checkTest_false_throwsException() {
        Environment env = mockEnv("prod");
        ProfileChecker profileChecker = new ProfileChecker(env);
        profileChecker.readActiveProfiles();

        Executable check = profileChecker::checkTest;
        assertThrows(InternalException.class, check);
    }


    private Environment mockEnv(String... profile) {
        Environment env = Mockito.mock(Environment.class);
        given(env.getActiveProfiles()).willReturn(profile);
        return env;
    }

    private Executable constructWithPostConstruct(Environment env) {
        ProfileChecker profileChecker = new ProfileChecker(env);
        return profileChecker::readActiveProfiles;
    }

}
