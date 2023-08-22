package team.yellow.docconnect.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
public class PropertyVariables {

    @Value("${confirm.email.uri}")
    private String emailUri;

    @Value("${reset.password.uri}")
    private String passwordUri;

    @Value("${redirect.uri}")
    private String redirectUri;

}
