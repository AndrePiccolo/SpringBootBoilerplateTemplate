package com.generalTemplate.adapter.rest;

import com.generalTemplate.adapter.config.Keycloak.entity.UserInfoKeycloak;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.GrantedAuthority;

@RestController
@RequestMapping("example")
public class ApiRest {

    @GetMapping("get")
    public ResponseEntity<String> getExample(
            @RequestParam(name = "exampleId") String exampleId,
            @RequestParam(name = "extraInformation") String extraInformation
    ) {
        return new ResponseEntity<>("Get Return", HttpStatus.OK);
    }

    @PostMapping("post")
    public ResponseEntity<String> postExample(
            @RequestParam(name = "exampleId") String exampleId,
            @RequestBody String extraInformation
    ) {
        return new ResponseEntity<>("Post Return", HttpStatus.OK);
    }

    //Get user information from Keycloack
    @GetMapping("/me")
    public UserInfoKeycloak getGretting(JwtAuthenticationToken auth) {
        return UserInfoKeycloak.builder()
                .name(auth.getToken().getClaimAsString(StandardClaimNames.PREFERRED_USERNAME))
                .roles(auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .build();
    }
}
