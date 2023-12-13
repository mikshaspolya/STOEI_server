package org.kursovoi.server.util.keycloak;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenUtil {

    private final KeycloakRealmRoleConverter converter;

    public String getUUIDUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        JwtAuthenticationToken auth = (JwtAuthenticationToken) context.getAuthentication();
        return auth.getName();
    }

    public boolean hasRole(RoleMapping role) {
        SecurityContext context = SecurityContextHolder.getContext();
        JwtAuthenticationToken auth = (JwtAuthenticationToken) context.getAuthentication();
        var roles = converter.convert(auth.getToken());
        return roles.contains(new SimpleGrantedAuthority(role.getRoleName()));
    }

}
