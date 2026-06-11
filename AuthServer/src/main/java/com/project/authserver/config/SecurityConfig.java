package com.project.authserver.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;

import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;

import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import java.util.List;
import java.util.UUID;

@Configuration
public class SecurityConfig {

    /**
     * AUTHORIZATION SERVER SECURITY
     */
    @Bean
    @Order(1)
    public SecurityFilterChain authorizationServerSecurityFilterChain(
            HttpSecurity http) throws Exception {

        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

        http
                .exceptionHandling(exceptions ->
                        exceptions.authenticationEntryPoint(
                                new LoginUrlAuthenticationEntryPoint("/login")
                        )
                )

                .oauth2ResourceServer(resourceServer ->
                        resourceServer.jwt(Customizer.withDefaults())
                );

        return http.build();
    }

    /**
     * APPLICATION SECURITY
     */
    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(
            HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        // Swagger
                        .requestMatchers(
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()

                        // Actuator
                        .requestMatchers(
                                "/actuator/**"
                        ).permitAll()

                        // Login
                        .requestMatchers(
                                "/login"
                        ).permitAll()

                        .anyRequest()
                        .authenticated()
                )

                .formLogin(Customizer.withDefaults());

        return http.build();
    }

    /**
     * USERS
     */
    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails user = User
                .withDefaultPasswordEncoder()
                .username("admin")
                .password("admin123")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user);
    }


    @Bean
    public RegisteredClientRepository registeredClientRepository() {

        RegisteredClient registeredClient =

                RegisteredClient.withId(UUID.randomUUID().toString())

                        .clientId("order-client")

                        .clientSecret("{noop}secret")

                        .clientAuthenticationMethod(
                                ClientAuthenticationMethod.CLIENT_SECRET_BASIC
                        )

                        // ONLY THIS
                        .authorizationGrantType(
                                AuthorizationGrantType.CLIENT_CREDENTIALS
                        )

                        .scope("read")
                        .scope("write")

                        .build();

        return new InMemoryRegisteredClientRepository(
                registeredClient
        );
    }

    /**
     * JWT CUSTOM CLAIMS
     */
    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> tokenCustomizer() {

        return context -> {

            if ("access_token".equals(
                    context.getTokenType().getValue())) {

                context.getClaims().claim(
                        "roles",
                        List.of("ROLE_ADMIN")
                );

                context.getClaims().claim(
                        "client_id",
                        context.getRegisteredClient().getClientId()
                );
            }
        };
    }

    /**
     * AUTHORIZATION SERVER SETTINGS
     */
    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {

        return AuthorizationServerSettings.builder()
                .issuer("http://localhost:9000")
                .build();
    }

    /**
     * JWK SOURCE
     */
    @Bean
    public JWKSource<SecurityContext> jwkSource() {

        RSAKey rsaKey = generateRsa();

        JWKSet jwkSet = new JWKSet(rsaKey);

        return (selector, context) ->
                selector.select(jwkSet);
    }

    /**
     * GENERATE RSA KEY
     */
    private RSAKey generateRsa() {

        try {

            KeyPairGenerator keyPairGenerator =
                    KeyPairGenerator.getInstance("RSA");

            keyPairGenerator.initialize(2048);

            KeyPair keyPair =
                    keyPairGenerator.generateKeyPair();

            return new RSAKey.Builder(
                    (RSAPublicKey) keyPair.getPublic()
            )
                    .privateKey(
                            (RSAPrivateKey) keyPair.getPrivate()
                    )
                    .keyID(UUID.randomUUID().toString())
                    .build();

        } catch (Exception ex) {

            throw new RuntimeException(ex);
        }
    }
}
