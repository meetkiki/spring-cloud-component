package com.thoughtworks.springboot.auto.configuration.core;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;

import java.util.List;

/**
 * 加载条件筛选器抽象
 */
public interface SpringBootLoadFilter extends Condition {

    /**
     * 自定义的过滤方法
     */
    void match(ConfigurableListableBeanFactory beanFactory, List<String> autoConfigurationClasses);
}
