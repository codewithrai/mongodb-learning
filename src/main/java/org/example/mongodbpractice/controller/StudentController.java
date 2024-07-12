package org.example.mongodbpractice.controller;

import org.example.mongodbpractice.document.Student;
import org.example.mongodbpractice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @PostMapping
    public ResponseEntity<Student> save(@RequestBody Student student) {
        Student save = studentRepository.save(student);
        return new ResponseEntity<>(save, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Student>> findAll() {
        List<Student> students = studentRepository.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
}
