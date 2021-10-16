package com.thoughtworks.springboot.auto.configuration.core;

import com.thoughtworks.springboot.auto.configuration.annotation.ConditionalOnClassPath;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.util.ClassUtils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class OnClassPathClassCondition implements SpringBootLoadFilter {

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
            final List<String> classes = AutoConfigurationConditionLoader.getCondition(autoConfigurationClass, "OnClassPathClass");

            // 判断
            final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            final boolean match = classes.stream().allMatch(clz -> ClassUtils.isPresent(clz, classLoader));

            // 如果不满足 移除加载
            if (!match) {
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
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(ConditionalOnClassPath.class.getName());
        if (annotationAttributes == null) {
            return false;
        }
        final Class<?>[] classes = (Class<?>[]) annotationAttributes.getOrDefault("value", new Class[]{});

        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return Arrays.stream(classes).allMatch(clz -> ClassUtils.isPresent(clz.getName(), classLoader));
    }

}
