package com.github.suloginscene.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "app")
@Getter @Setter
public class AppProperties {

    private String address = "http://localhost:8080";
    private String developer = "suloginscene@email.com";

}
