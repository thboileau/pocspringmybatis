package com.example.springdatajdbc.representation;

import com.example.springdatajdbc.model.Book;
import com.example.springdatajdbc.model.Student;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentToAdd {
    private String id;
    private String name;
    private List<Book> books;

    public Student getStudent() {
        return new Student(id, name, books);
    }
}
