package com.example.employeeapi.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatSubmit {

    enum TYPE{PARAM,TOKEN};

    TYPE limitType() default TYPE.PARAM;
    long lockTime() default 5;
}
