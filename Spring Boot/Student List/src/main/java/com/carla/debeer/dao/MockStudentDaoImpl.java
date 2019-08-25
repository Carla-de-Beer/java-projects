package com.carla.debeer.dao;

import com.carla.debeer.entity.Student;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
@Qualifier("mockData")
public class MockStudentDaoImpl implements StudentDao {

    private static Map<Long, Student> students = Stream.of(new Object[][] {
            { 1L, new Student(1L, "Said", "Computer Science")},
            { 2L, new Student(2L, "Alex", "Finance")},
            { 3L, new Student(3L, "Anna", "Maths")},
    }).collect(Collectors.toMap(data -> (Long) data[0], data -> (Student) data[1]));

    @Override
    public Collection<Student> getAllStudents() {
        return this.students.values();
    }

    @Override
    public Student getStudentById(long id) {
        return this.students.get(id);
    }

    @Override
    public void removeStudentById(long id) {
        this.students.remove(id);
    }

    @Override
    public void updateStudent(Student student) {
        Student s = students.get(student.getId());
        s.setCourse(student.getCourse());
        s.setName(student.getName());
        students.put(student.getId(), student);
    }

    @Override
    public void addStudent(Student student) {
        students.put(student.getId(), student);
    }
}
