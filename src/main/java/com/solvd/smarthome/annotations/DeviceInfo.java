package com.solvd.smarthome.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DeviceInfo {

    String label() default "";

    String version() default "1.0";


    boolean includeInReport() default true;

    String description() default "No description provided.";
}