package org.example.mongodbpractice.repository;

import org.example.mongodbpractice.document.CarrierSheet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarrierRepository extends MongoRepository<CarrierSheet, String > {
}
