package com.example.springdatajdbc.mapper;

import com.example.springdatajdbc.model.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface StudentMapper {

    @Select("SELECT id, name FROM student") // spécifier les colonnes et ne jamais utiliser "*"
    List<Student> findAll();

    @Insert("INSERT INTO student (id, name) VALUES(#{id}, #{name})")
    @Options(useGeneratedKeys = false, keyProperty = "id")
    void insert(Student student);

    @Insert("UPDATE student SET name = #{name} WHERE id = #{id}")
    void save(Student student);

    @Select("SELECT id, name FROM student WHERE id = #{id}")
    @Results({// le mapping colonne/pté est nécessaire quand il n'y a pas homonymie (optionel donc dans cet exemple)
            @Result(column = "id", property = "id")
    })
    Student findByIdWithoutBooks(String id);

    @Delete("DELETE FROM student WHERE id = #{id}")
    void deleteById(String id);

}
