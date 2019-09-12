package com.cadebe.github_reader.controller

import org.springframework.lang.Nullable
import spock.lang.Specification
import spock.lang.Subject

class ErrorControllerTest extends Specification {

    def mockModel = new org.springframework.ui.Model() {

        @Override
        org.springframework.ui.Model addAttribute(String s, @Nullable Object o) {
            return null
        }

        @Override
        org.springframework.ui.Model addAttribute(Object o) {
            return null
        }

        @Override
        org.springframework.ui.Model addAllAttributes(Collection<?> collection) {
            return null
        }

        @Override
        org.springframework.ui.Model addAllAttributes(Map<String, ?> map) {
            return null
        }

        @Override
        org.springframework.ui.Model mergeAttributes(Map<String, ?> map) {
            return null
        }

        @Override
        boolean containsAttribute(String s) {
            return false
        }

        @Override
        Map<String, Object> asMap() {
            return null
        }
    }

    @Subject
    def errorController = new ErrorController()

    def "ErrorController: handleError()"() {
        given: "An exception and a model"
        def exception = new IOException()
        exception.initCause(new Throwable())
        mockModel.addAttribute("errorMsg", "Things went wrong!")

        when: "calling handleError()"
        def result = errorController.handleError(exception, mockModel)

        then: "handleError() successfully called"
        result != null
        result == "error"
    }
}
