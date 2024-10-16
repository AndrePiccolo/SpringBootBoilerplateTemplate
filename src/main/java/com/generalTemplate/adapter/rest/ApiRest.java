package com.generalTemplate.adapter.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("example")
public class ApiRest {

    @GetMapping("public/get")
    public ResponseEntity<String> getExample(
            @RequestParam(name = "exampleId") String exampleId,
            @RequestParam(name = "extraInformation") String extraInformation
    ) {
        return new ResponseEntity<>("Get Return", HttpStatus.OK);
    }

    @PostMapping("public/post")
    public ResponseEntity<String> postExample(
            @RequestParam(name = "exampleId") String exampleId,
            @RequestBody String extraInformation
    ) {
        return new ResponseEntity<>("Post Return", HttpStatus.OK);
    }
}
