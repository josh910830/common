package com.github.suloginscene.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "security")
@Getter @Setter
public class SecurityProperties {

    private String secret = "secret";
    private String origins = "http://localhost:3000,https://frontend.com";
    private int expMin = 30;
    private int refreshExpDay = 14;

}
