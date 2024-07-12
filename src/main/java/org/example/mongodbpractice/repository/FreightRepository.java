package org.example.mongodbpractice.repository;

import org.example.mongodbpractice.document.Freight;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FreightRepository extends MongoRepository<Freight, String> {

}
