package org.example.mongodbpractice.controller;

import org.example.mongodbpractice.document.Transport;
import org.example.mongodbpractice.repository.TransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transport")
public class TransportController {
    @Autowired
    private TransportRepository transportRepository;

    @PostMapping
    public ResponseEntity<Transport> save(@RequestBody Transport transport) {
        return new ResponseEntity<>(transportRepository.save(transport), HttpStatus.OK);
    }
}
