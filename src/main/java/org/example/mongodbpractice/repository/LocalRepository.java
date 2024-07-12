package org.example.mongodbpractice.repository;

import org.example.mongodbpractice.document.Local;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocalRepository extends MongoRepository<Local, String> {
}
