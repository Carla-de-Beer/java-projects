package com.carla.debeer.service;

import com.carla.debeer.dao.MockStudentDaoImpl;
import com.carla.debeer.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class StudentService {

    @Autowired
    @Qualifier("mockData")
    public MockStudentDaoImpl studentDao;

    public Collection<Student> getAllStudents() {
        return this.studentDao.getAllStudents();
    }

    public Student getStudentById(int id) {
        return this.studentDao.getStudentById(id);
    }

    public void removeStudentById(int id) {
        this.studentDao.removeStudentById(id);
    }

    public void updateStudent(Student student) {
        this.studentDao.updateStudent(student);
    }

    public void addStudent(Student student) {
        this.studentDao.addStudent(student);
    }
}
