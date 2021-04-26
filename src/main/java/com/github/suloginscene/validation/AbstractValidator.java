package com.github.suloginscene.validation;

import lombok.NonNull;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public abstract class AbstractValidator implements Validator {

    private static final String ERROR_CODE = "n/a";


    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return targetClass().isAssignableFrom(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        if (!isValid(target)) {
            reject(errors);
        }
    }

    private void reject(Errors errors) {
        errors.rejectValue(targetField(), ERROR_CODE, rejectMessage());
    }


    protected abstract Class<?> targetClass();

    protected abstract String targetField();

    protected abstract String rejectMessage();

    protected abstract boolean isValid(Object target);

}
