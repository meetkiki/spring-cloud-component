package com.thoughtworks.springboot.auto.configuration.core;

import com.thoughtworks.springboot.auto.configuration.annotation.ConditionalOnBeanPresent;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.ConfigurationCondition;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class OnBeanPresentCondition implements SpringBootLoadFilter,ConfigurationCondition {

    /**
     * 实现ConfigurationCondition 设置判断的时机
     *  PARSE_CONFIGURATION 当解析CONFIGURATION时
     *  REGISTER_BEAN 注册成bean定义
     */
    @Override
    public ConfigurationCondition.ConfigurationPhase getConfigurationPhase() {
        return ConfigurationCondition.ConfigurationPhase.REGISTER_BEAN;
    }

    /**
     * 自定义的过滤方法
     *
     * @param autoConfigurationClasses
     */
    @Override
    public void match(ConfigurableListableBeanFactory beanFactory, List<String> autoConfigurationClasses) {
        final Iterator<String> classIterator = autoConfigurationClasses.iterator();

        while (classIterator.hasNext()) {
            final String autoConfigurationClass = classIterator.next();

            // 拿到配置的条件
            final List<String> beans = AutoConfigurationConditionLoader.getCondition(autoConfigurationClass, "OnBeanPresent");

            // 判断
            final boolean match = beans.stream().allMatch(beanFactory::containsBean);

            // 如果不满足 移除加载
            if (!match){
                classIterator.remove();
            }
        }
    }


    /**
     * Determine if the condition matches.
     *
     * @param context  the condition context
     * @param metadata the metadata of the {@link AnnotationMetadata class}
     *                 or {@link MethodMetadata method} being checked
     * @return {@code true} if the condition matches and the component can be registered,
     * or {@code false} to veto the annotated component's registration
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(ConditionalOnBeanPresent.class.getName());
        if (annotationAttributes == null) {
            return false;
        }
        final Class<?>[] beans = (Class<?>[]) annotationAttributes.getOrDefault("value", new Class<?>[]{});
        final ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

        return Arrays.stream(beans).allMatch(beanName -> Objects.requireNonNull(beanFactory).getBeanNamesForType(beanName).length != 0);
    }


}
