package com.example.demo.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Range {
    int integerMax() default 130;
    int integerMin() default 1;
    int stringMax() default 20;
    int stringMin() default 0;
}
