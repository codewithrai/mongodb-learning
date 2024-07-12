package org.example.mongodbpractice.repository;

import org.example.mongodbpractice.document.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {

}
