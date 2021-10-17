package com.thoughtworks.springboot.auto.configuration.annotation;


import com.thoughtworks.springboot.auto.configuration.core.OnMissBeanCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 当registry中不存在某个类的bean定义时加载
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Conditional(OnMissBeanCondition.class)
public @interface ConditionalOnMissBean {

    Class<?>[] value();

}
