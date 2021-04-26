package com.github.suloginscene.profile;

import com.github.suloginscene.exception.InternalException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

import static com.github.suloginscene.profile.Profiles.LOCAL;
import static com.github.suloginscene.profile.Profiles.PROD;
import static com.github.suloginscene.profile.Profiles.TEST;


@Component
@RequiredArgsConstructor
public class ProfileChecker {

    private final Environment environment;

    private String activeProfile;


    @PostConstruct
    void readActiveProfiles() {
        String[] activeProfiles = environment.getActiveProfiles();
        if (activeProfiles.length == 0) throw new InternalException("profile is unset");
        if (activeProfiles.length > 1) throw new InternalException("profile is not unique");

        activeProfile = activeProfiles[0];
        if (!List.of(LOCAL, TEST, PROD).contains(activeProfile)) throw new InternalException("profile is unknown");
    }


    public void checkTest() {
        if (!activeProfile.equals(TEST)) {
            throw new InternalException("profile is not test");
        }
    }

}
