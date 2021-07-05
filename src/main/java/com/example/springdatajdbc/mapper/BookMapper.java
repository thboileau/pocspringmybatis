package com.example.springdatajdbc.mapper;

import com.example.springdatajdbc.model.Book;
import com.example.springdatajdbc.model.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BookMapper {

    @Insert("INSERT INTO BOOK (id, name, student_id) VALUES(#{book.id}, #{book.name}, #{studentId})")
    void insert(Book book, String studentId);

    @Select("DELETE FROM book WHERE student_id=#{studentId}")
    Student deleteBooksByStudentId(String studentId);
}
