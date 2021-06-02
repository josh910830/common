package com.github.suloginscene.validation;

import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.MapBindingResult;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("추상 발리데이터")
class AbstractValidatorTest {

    AbstractValidator abstractValidator;


    @BeforeEach
    void setup() {
        abstractValidator = new AbstractValidator() {
            @Override
            protected Class<?> targetClass() {
                return Target.class;
            }

            @Override
            protected String targetField() {
                return "value";
            }

            @Override
            protected String rejectMessage() {
                return "max length is 4";
            }

            @Override
            protected boolean isValid(Object object) {
                Target target = (Target) object;
                String value = target.value;
                return value.length() <= 4;
            }
        };
    }

    @Test
    @DisplayName("검증 대상 확인")
    void support() {
        boolean supportsTarget = abstractValidator.supports(Target.class);
        boolean supportsString = abstractValidator.supports(String.class);
        boolean supportsObject = abstractValidator.supports(Object.class);
        boolean supportsConcreteTarget = abstractValidator.supports(ConcreteTarget.class);

        assertThat(supportsTarget).isTrue();
        assertThat(supportsString).isFalse();
        assertThat(supportsObject).isFalse();
        assertThat(supportsConcreteTarget).isTrue();
    }

    @Test
    @DisplayName("검증 - 적합")
    void validate_onValid_hasNoErrors() {
        Target target = new Target("1234");
        Errors errors = new MapBindingResult(new HashMap<>(), "target");

        abstractValidator.validate(target, errors);

        assertThat(errors.hasErrors()).isFalse();
    }

    @Test
    @DisplayName("검증 - 부적합")
    void validate_onInvalid_hasErrors() {
        Target target = new Target("12345");
        Errors errors = new MapBindingResult(new HashMap<>(), "target");

        abstractValidator.validate(target, errors);

        assertThat(errors.hasErrors()).isTrue();

        List<FieldError> fieldErrors = errors.getFieldErrors();
        assertThat(fieldErrors.size()).isEqualTo(1);

        FieldError fieldError = fieldErrors.get(0);
        assertThat(fieldError.getField()).isEqualTo("value");
        assertThat(fieldError.getDefaultMessage()).isEqualTo("max length is 4");
    }

    @Data
    static class Target {
        private final String value;
    }

    static class ConcreteTarget extends Target {
        public ConcreteTarget(String value) {
            super(value);
        }
    }

}
