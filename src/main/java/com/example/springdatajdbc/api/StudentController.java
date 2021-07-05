package com.example.springdatajdbc.api;

import com.example.springdatajdbc.model.Student;
import com.example.springdatajdbc.representation.StudentToAdd;
import com.example.springdatajdbc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/students")
    private List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/students")
    private String addStudent(@RequestBody StudentToAdd student) {
        studentService.create(student.getStudent());
        return student.getId();
    }

    @PutMapping("/students/{id}")
    private String saveStudent(@RequestBody StudentToAdd student) {
        studentService.update(student.getStudent());
        return student.getId();
    }

    @GetMapping("/students/{id}")
    private Student getStudentByIdWithBooks(@PathVariable("id") String id) {
        return studentService.getStudentByIdWithBooks(id);
    }

    @DeleteMapping("/students/{id}")
    private void deleteStudent(@PathVariable("id") String id) {
        studentService.deleteById(id);
    }

    // juste pour illustrer la possibilité de ne récupérer que les infos de la table Student
    @GetMapping("/students/{id}/withoutBooks")
    private Student getStudentByIdWithoutBooks(@PathVariable("id") String id) {
        return studentService.getStudentByIdWithoutBooks(id);
    }

}
