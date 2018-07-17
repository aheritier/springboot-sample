package com.example.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping(path = "/api/data", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    @Nonnull
    @RequestMapping(value = "/{identifier}/file", method = RequestMethod.POST)
    public ResponseEntity<String> upload(
            @Nonnull @PathVariable String identifier,
            @Nonnull @RequestPart("file") FilePart file) throws IOException {
        File temp = File.createTempFile("temp-file-name", ".tmp");
        file.transferTo(temp);
        LOGGER.info("Content of the file: {}", Files.readAllLines(Paths.get(temp.getAbsolutePath())));
        temp.delete();
        return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
    }

}
