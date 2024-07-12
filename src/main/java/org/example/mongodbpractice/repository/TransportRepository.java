package org.example.mongodbpractice.repository;

import org.example.mongodbpractice.document.Transport;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransportRepository extends MongoRepository<Transport, String> {

}
