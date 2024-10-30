package com.generalTemplate.adapter.rest;

import com.generalTemplate.adapter.config.keycloak.entity.UserInfoKeycloak;
import com.generalTemplate.adapter.database.entity.EntityExample;
import com.generalTemplate.adapter.port.RepositoryExampleInterface;
import com.generalTemplate.adapter.rest.entity.pdf.PdfContent;
import com.generalTemplate.adapter.rest.entity.testdb.DBEntryTest;
import com.generalTemplate.adapter.util.PdfCreator;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("example")
public class ApiRest {

    @Autowired
    private PdfCreator pdfCreator;
    @Autowired
    private RepositoryExampleInterface repositoryExample;

    @PostMapping("database/addInformation")
    public ResponseEntity<EntityExample> createDBEntry(@RequestBody DBEntryTest dbEntry) {

        return new ResponseEntity<>(repositoryExample.CreateExampleEntry(EntityExample.builder()
                .date(dbEntry.getDate())
                .comments(dbEntry.getComments())
                .build()), HttpStatus.OK);
    }

    @GetMapping("database/getInformation")
    public ResponseEntity<EntityExample> getDBEntry(@RequestParam(name = "id") String id) {
        return new ResponseEntity<>(repositoryExample.GetExampleEntry(id), HttpStatus.OK);
    }

    @GetMapping("database/getAllInformation")
    public ResponseEntity<List<EntityExample>> getAllDBEntry() {
        return new ResponseEntity<>(repositoryExample.GetAllExampleEntry(), HttpStatus.OK);
    }

    @PutMapping("database/updateInformation")
    public ResponseEntity<EntityExample> updateDBEntry(@RequestBody DBEntryTest dbEntry) {
        return new ResponseEntity<>(repositoryExample.UpdateExampleEntry(EntityExample.builder()
                .id(dbEntry.getId())
                .date(dbEntry.getDate())
                .comments(dbEntry.getComments())
                .build()), HttpStatus.OK);
    }

    @DeleteMapping("database/deleteInformation")
    public ResponseEntity<String> deleteDBEntry(@RequestParam(name = "id") String id) {
        repositoryExample.DeleteExampleEntry(id);
        return new ResponseEntity<>("Entry deleted", HttpStatus.OK);
    }

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
    public ResponseEntity<byte[]> getPDFExample(@RequestBody PdfContent docContent) throws DocumentException, IOException {

        ByteArrayOutputStream pdfDocument = pdfCreator.createPdf(docContent);

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