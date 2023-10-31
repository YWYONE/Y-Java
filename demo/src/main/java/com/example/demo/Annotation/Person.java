package com.example.demo.Annotation;

import lombok.Data;

@Data
public class Person {
    @Range
    String name;
    @Range
    int age;

}
