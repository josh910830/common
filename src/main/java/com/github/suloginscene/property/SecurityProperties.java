package com.github.suloginscene.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "security")
@Getter @Setter
public class SecurityProperties {

    private String secret;
    private String origins;
    private int expMin;
    private int refreshExpDay;

}
