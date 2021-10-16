package com.thoughtworks.springboot.auto.configuration.annotation;


import com.thoughtworks.springboot.auto.configuration.core.AutoConfigureImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 开启自动装配的注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AutoConfigureImportSelector.class)
public @interface AutoConfiguration {

}
