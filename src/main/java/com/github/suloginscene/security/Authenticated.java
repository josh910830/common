package com.github.suloginscene.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Retention(RUNTIME)
@Target(PARAMETER)
@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : memberId")
public @interface Authenticated {

}
