package org.example.mongodbpractice.controller;

import org.example.mongodbpractice.document.CarrierSheet;
import org.example.mongodbpractice.dto.AggregationResultDTO;
import org.example.mongodbpractice.repository.CarrierRepository;
import org.example.mongodbpractice.service.CarrierCriteriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrier")
public class CarrierController {
    @Autowired
    private CarrierCriteriaRepository carrierCriteriaRepository;
    @Autowired
    private CarrierRepository carrierRepository;

    @PostMapping
    public ResponseEntity<CarrierSheet> save(@RequestBody CarrierSheet carrierSheet) {
        return new ResponseEntity<>(carrierRepository.save(carrierSheet), HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<AggregationResultDTO>> findCarrierCriteria(@RequestParam("searchQuery") String searchQuery,
                                                          @RequestParam("majorTransportTypeFlag") String majorTransportTypeFlag,
                                                          @RequestParam("sortByField") String sortByField,
                                                          @RequestParam("sortOrder") String sortOrder,
                                                          @RequestParam("page") int page,
                                                          @RequestParam("size") int size) {
        List<AggregationResultDTO> carrierCriteria = carrierCriteriaRepository.findCarrierCriteria(searchQuery, sortByField, sortOrder, page, size, majorTransportTypeFlag);
        return new ResponseEntity<>(carrierCriteria, HttpStatus.OK);
    }
}
