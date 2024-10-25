package com.generalTemplate.adapter.config.Keycloak;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityOAuth2 {

    interface AuthoritiesConverter extends Converter<Map<String, Object>, Collection<GrantedAuthority>> {}

    @Bean
    @SuppressWarnings("unchecked")
    AuthoritiesConverter realmRolesAuthoritiesConverter() {
        return claims -> {
            final Optional<Map<String, Object>> realmAccess = Optional.ofNullable((Map<String, Object>) claims.get("realm_access"));
            final Optional<List<String>> roles = realmAccess.flatMap(map -> Optional.ofNullable((List<String>) map.get("roles")));
            return roles.stream().flatMap(Collection::stream).map(SimpleGrantedAuthority::new)
                    .map(GrantedAuthority.class::cast).toList();
        };
    }

    @Bean
    JwtAuthenticationConverter authenticationConverter(
            Converter<Map<String, Object>, Collection<GrantedAuthority>> authoritiesConverter) {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> authoritiesConverter.convert(jwt.getClaims()));
        return jwtAuthenticationConverter;
    }

    @Bean
    SecurityFilterChain resourceServerSecurityFilterChain(HttpSecurity http,
                                                          Converter<Jwt, AbstractAuthenticationToken> jwtAuthenticationConverter) throws Exception {
        http.oauth2ResourceServer(resourceServer -> {
            resourceServer.jwt(jwtDecoder -> {
                jwtDecoder.jwtAuthenticationConverter(jwtAuthenticationConverter);
            });
        });

        http.sessionManagement(sessions -> {
            sessions.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }).csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(requests -> {
            requests.anyRequest().authenticated();
        });

        return http.build();
    }

    //Using OAuth2 RestTemplate with old SpringBoot libraries uncomment 2 methods below
//    @Bean
//    protected OAuth2ProtectedResourceDetail oauth2Resource(){
//        ClientCredentialsResourceDetails client = new ClientCredentialsResourceDetails();
//        client.setAccessTokenUri("tokenUrl");
//        client.setClientId("clientId");
//        client.setClientSecret("clientSecret");
//        client.setGrantType("grantType");
//        client.setScope("scope");
//        client.setAuthenticationScheme(AuthenticationScheme.header);
//        return client;
//    }
//
//    @Bean
//    public OAuth2RestTemplate oAuth2RestTemplate(){
//        AccessTokenRequest atr = new DefaultAccessTokenRequest();
//        OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(oauth2Resource(), new DefaultOAuth2ClientContext(atr));
//        OAuth2RestTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
//        return oAuth2RestTemplate;
//    }
}
