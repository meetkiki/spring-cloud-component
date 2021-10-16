package com.thoughtworks.springboot.auto.configuration.annotation;


import com.thoughtworks.springboot.auto.configuration.core.OnBeanPresentCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 当registry中存在某个类的bean定义时加载
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Conditional(OnBeanPresentCondition.class)
public @interface ConditionalOnBeanPresent {

    Class<?>[] value();

}
