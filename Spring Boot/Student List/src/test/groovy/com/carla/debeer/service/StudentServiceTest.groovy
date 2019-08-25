package com.carla.debeer.service

import com.carla.debeer.dao.MockStudentDaoImpl
import com.carla.debeer.entity.Student
import spock.lang.Specification
import spock.lang.Subject

class StudentServiceTest extends Specification {

    @Subject
    def studentService

    def setup() {
        studentService = new StudentService()
        studentService.studentDao = new MockStudentDaoImpl()
    }

    def "StudentServiceTest: getAllStudents()"() {
        when: "calling getAllStudents()"
        ArrayList<Student> result = studentService.getAllStudents()

        then: "getAllStudents() has been successfully called"
        result != null
        result.getClass() == java.util.ArrayList
    }
}
