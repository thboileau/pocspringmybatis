package com.example.springdatajdbc.service;

import com.example.springdatajdbc.mapper.BookMapper;
import com.example.springdatajdbc.mapper.StudentMapper;
import com.example.springdatajdbc.model.Student;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentMapper studentMapper;
    private final BookMapper bookMapper;
    private final SqlSession session; // ne nous sert qu'à envoyer des requêtes issues d'un mapper XML

    public StudentService(final StudentMapper studentMapper, final BookMapper bookMapper, final SqlSession session) {
        this.studentMapper = studentMapper;
        this.bookMapper = bookMapper;
        this.session = session;
    }

    public List<Student> getAllStudents() {
        return studentMapper.findAll();
    }

    public Student getStudentByIdWithBooks(String id) {
        return session.selectOne("selectStudentJoin", id); // => check xml mapper
    }

    public Student getStudentByIdWithoutBooks(String id) {
        return studentMapper.findByIdWithoutBooks(id);
    }

    public void create(Student student) {
        // devrait être englobé dans une transaction
        studentMapper.insert(student);
        insertNewBooksIfAny(student);
    }

    public void update(Student student) {
        // devrait être englobé dans une transaction
        studentMapper.save(student);
        bookMapper.deleteBooksByStudentId(student.getId()); // brutaaaal!
        insertNewBooksIfAny(student);
    }

    public void deleteById(String id) {
        studentMapper.deleteById(id);
    }

    private void insertNewBooksIfAny(final Student student) {
        if (student.getBooks() != null) {
            student.getBooks()
                    .forEach(book -> bookMapper.insert(book, student.getId()));
        }
    }
}
