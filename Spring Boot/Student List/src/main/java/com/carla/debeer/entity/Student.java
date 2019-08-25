package com.carla.debeer.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@NoArgsConstructor
@Slf4j
@Builder
public class Student {

    private long id;
    private String name;
    private String course;

    public Student(long id, String name, String course) {
        this.id = id;
        this.name = name;
        this.course = course;
        log.info("The student object has been created.");
    }
}
