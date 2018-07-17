package com.example.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nonnull;

@RestController
@RequestMapping(path = "/api/data", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller {

    @Nonnull
    @RequestMapping(value = "/{identifier}/file", method = RequestMethod.POST)
    public ResponseEntity<String> upload(
            @Nonnull @PathVariable String identifier,
            @Nonnull @RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
    }

}
