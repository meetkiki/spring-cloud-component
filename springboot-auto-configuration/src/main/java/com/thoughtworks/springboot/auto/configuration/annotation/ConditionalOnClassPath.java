package com.thoughtworks.springboot.auto.configuration.annotation;


import com.thoughtworks.springboot.auto.configuration.core.OnClassPathClassCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 当classpath中存在某个类时加载
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Conditional(OnClassPathClassCondition.class)
public @interface ConditionalOnClassPath {

    Class<?>[] value();

}
