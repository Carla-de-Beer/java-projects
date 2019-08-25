package com.carla.debeer.dao;

import com.carla.debeer.entity.Student;

import java.util.Collection;

public interface StudentDao {
    Collection<Student> getAllStudents();

    Student getStudentById(long id);

    void removeStudentById(long id);

    void updateStudent(Student student);

    void addStudent(Student student);
}
