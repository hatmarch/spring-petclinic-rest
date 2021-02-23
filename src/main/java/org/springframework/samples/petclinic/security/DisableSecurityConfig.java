package org.springframework.samples.petclinic.security;

import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.security.spi.runtime.AuthorizationController;

import javax.enterprise.context.ApplicationScoped;

/**
 * Starting from Spring Boot 2, if Spring Security is present, endpoints are secured by default
 * using Spring Security’s content-negotiation strategy.
 */
@ApplicationScoped
public class DisableSecurityConfig extends AuthorizationController {

    @ConfigItem(name = "petclinic.security.enable", defaultValue = "true")
    boolean enabled;

    @Override
    public boolean isAuthorizationEnabled() {
        return enabled;
    }

}
