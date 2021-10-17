package com.thoughtworks.springboot.auto.configuration.core;


import com.thoughtworks.springboot.auto.configuration.annotation.AutoConfiguration;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

public class AutoConfigureImportSelector implements ImportSelector, BeanFactoryAware, BeanClassLoaderAware {

    private ConfigurableListableBeanFactory beanFactory;

    private ClassLoader beanClassLoader;

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        List<String> autoConfigs = SpringFactoriesLoader.loadFactoryNames(AutoConfiguration.class, beanClassLoader);

        // 去重
        autoConfigs = new ArrayList<>(new LinkedHashSet<>(autoConfigs));

        // 按照条件过滤
        final List<SpringBootLoadFilter> springBootLoadFilters = getSpringBootLoadFilters();
        for (SpringBootLoadFilter springBootLoadFilter : springBootLoadFilters) {
            springBootLoadFilter.match(beanFactory, autoConfigs);
        }

        return autoConfigs.toArray(new String[0]);
    }

    public Class<? extends DeferredImportSelector.Group> getImportGroup() {
        return AutoConfigureGroup.class;
    }


    private List<SpringBootLoadFilter> getSpringBootLoadFilters() {
        return SpringFactoriesLoader.loadFactories(SpringBootLoadFilter.class, beanClassLoader);
    }


    private static class AutoConfigureGroup implements DeferredImportSelector.Group {

        private Entry[] entries;

        @Override
        public void process(AnnotationMetadata metadata, DeferredImportSelector selector) {
            // 通过SpringFactoriesLoader 加载所有classpath下的META-INF/spring.factories文件配置的自动装配类
            final String[] selectImports = selector.selectImports(metadata);
            this.entries = Arrays.stream(selectImports).map(select -> new Entry(metadata, select)).toArray(Entry[]::new);
        }

        @Override
        public Iterable<Entry> selectImports() {
            // 注解排序 Order 等
            return Arrays.stream(entries).collect(Collectors.toList());
        }
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }


    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.beanClassLoader = classLoader;
    }
}
