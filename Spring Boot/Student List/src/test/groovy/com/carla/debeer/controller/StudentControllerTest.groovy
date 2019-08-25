package com.carla.debeer.controller

import com.carla.debeer.dao.MockStudentDaoImpl
import com.carla.debeer.entity.Student
import com.carla.debeer.service.StudentService
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

class StudentControllerTest extends Specification {

    @Shared
    def studentService

    def setup() {
        studentService = new StudentService()
        studentService.studentDao = new MockStudentDaoImpl()
    }

    @Subject
    def studentController = new StudentController()

    def "StudentControllerTest: getAllStudents()"() {
        studentController.setStudentService(studentService)

        when: "calling getAllStudents()"
        Collection<Student> result = studentController.getAllStudents()

        then: "getAllStudents() has been successfully called"
        result != null
        result.getClass() == java.util.HashMap$Values
    }
}
