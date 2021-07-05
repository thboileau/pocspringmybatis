package com.example.springdatajdbc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class Student {
    private final String id;
    private final String name;
    private final List<Book> books;

    public Student(final String id, final String name) {
        this(id, name, new ArrayList<>());
    }
}
