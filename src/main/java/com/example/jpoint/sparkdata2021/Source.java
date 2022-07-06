package com.example.jpoint.sparkdata2021;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Source {
    String value();
}
