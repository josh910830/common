package com.github.suloginscene.string;

import com.github.suloginscene.exception.InternalException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DisplayName("Href 조합 유틸리티")
class HrefAssembleUtilTest {

    @Test
    @DisplayName("주소 설정 전 - 예외")
    void beforeSetAddress_throwsException() {
        HrefAssembleUtil.setAddress(null);
        Executable action = () -> HrefAssembleUtil.href("/path");
        assertThrows(InternalException.class, action);
    }

    @Test
    @DisplayName("주소 경로 조합")
    void assembleAddressAndPath() {
        HrefAssembleUtil.setAddress("https://domain.com");
        String href = HrefAssembleUtil.href("/path");
        assertThat(href).isEqualTo("https://domain.com/path");
    }

}
