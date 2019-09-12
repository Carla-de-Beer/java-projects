package com.cadebe.github_reader.controller

import com.cadebe.github_reader.service.ReaderService
import org.springframework.lang.Nullable
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

class ReaderControllerTest extends Specification {

    @Shared
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

    @Shared
    def readerService = Mock(ReaderService)

    @Subject
    def readerController = new ReaderController(readerService)

    def "ReaderController: greetingForm()"() {
        given: "A mock model"
        mockModel.addAttribute("token", "tokenText")

        when: "calling greetingForm()"
        def result = readerController.greetingForm(mockModel)

        then: "greetingForm() successfully called"
        result != null
        result == "index"
    }
}