package org.kursovoi.server.dto.keycloak;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.io.Serializable;

@Data
public class KeycloakAuthResponse implements Serializable {

    @JsonAlias("access_token")
    private String accessToken;
    @JsonAlias("refresh_token")
    private String refreshToken;
    @JsonAlias("token_type")
    private String tokenType;
    private String scope;
}
