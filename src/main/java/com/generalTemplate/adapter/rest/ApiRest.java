package com.generalTemplate.adapter.rest;

import com.generalTemplate.adapter.config.Keycloak.entity.UserInfoKeycloak;
import com.generalTemplate.adapter.rest.pdf.entity.PdfContent;
import com.generalTemplate.adapter.util.PdfCreator;
import com.itextpdf.text.DocumentException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.ByteArrayOutputStream;

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

    @PostMapping("get_pdf")
    public ResponseEntity<byte[]> getPDFExample(@RequestBody PdfContent docContent) throws DocumentException{
        PdfCreator pdf = new PdfCreator();
        ByteArrayOutputStream pdfDocument = pdf.createPdf(docContent);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=PdfDocumentCreated.pdf");
        return new ResponseEntity<>(pdfDocument.toByteArray(), headers, HttpStatus.OK);
    }

    //Get user information from Keycloak
    @GetMapping("/me")
    public UserInfoKeycloak getKeycloakUserInformation(JwtAuthenticationToken auth) {
        return UserInfoKeycloak.builder()
                .name(auth.getToken().getClaimAsString(StandardClaimNames.PREFERRED_USERNAME))
                .roles(auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .build();
    }
}