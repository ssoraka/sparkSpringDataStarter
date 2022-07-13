package com.example.unsafe_sparkdata;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ForiegnKey {
    String value();
}
