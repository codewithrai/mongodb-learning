package org.example.mongodbpractice.controller;

import org.example.mongodbpractice.document.Freight;
import org.example.mongodbpractice.repository.FreightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/freight")
public class FreightController {
    @Autowired
    private FreightRepository freightRepository;

    @PostMapping
    public ResponseEntity<Freight> save(@RequestBody Freight freight) {
        return new ResponseEntity<>(freightRepository.save(freight), HttpStatus.OK);
    }
}
