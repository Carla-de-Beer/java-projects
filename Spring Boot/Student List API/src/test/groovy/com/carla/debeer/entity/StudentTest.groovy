package com.carla.debeer.entity

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

class StudentTest extends Specification {

    @Shared
    def id = 5

    @Shared
    def name = "name"

    @Shared
    def surname = "surname"

    @Subject
    def student = new Student(id, name, surname)

    def "StudentTest: getId()"() {
        when: "calling getId()"
        def result = student.getId()

        then: "getId() has been successfully called"
        result == id
    }

    def "StudentTest: getName()"() {
        when: "calling getName()"
        def result = student.getName()

        then: "getName() has been successfully called"
        result == name
    }

    def "StudentTest: setName()"() {
        given:
        def newName = "newName"

        when: "calling setName()"
        student.setName(newName)

        then: "setName() has been successfully called"
    }
}
