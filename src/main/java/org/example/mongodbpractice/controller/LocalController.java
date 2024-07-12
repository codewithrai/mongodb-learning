package org.example.mongodbpractice.controller;

import org.example.mongodbpractice.document.Local;
import org.example.mongodbpractice.repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/local")
public class LocalController {
    @Autowired
    private LocalRepository localRepository;

    @PostMapping
    public ResponseEntity<Local> save(@RequestBody Local local) {
        return new ResponseEntity<>(localRepository.save(local), HttpStatus.OK);
    }
}
