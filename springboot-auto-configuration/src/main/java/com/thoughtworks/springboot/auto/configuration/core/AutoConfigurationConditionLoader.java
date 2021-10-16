package com.thoughtworks.springboot.auto.configuration.core;

import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class AutoConfigurationConditionLoader {
    protected static final String PATH = "META-INF/spring-autoconfig.properties";

    private AutoConfigurationConditionLoader() {
    }


    private static Map<String, List<String>> CONFIG_CONDITIONS;


    public static synchronized List<String> getCondition(String className, String key) {
        if (CONFIG_CONDITIONS == null) {
            CONFIG_CONDITIONS = new HashMap<>();
            // 加载classPath下所有spring-autoconfig.properties文件的条件配置
            loadConfigConditions(PATH);
        }
        return CONFIG_CONDITIONS.getOrDefault(className + "." + key, Collections.emptyList());
    }

    private static void loadConfigConditions(String path) {
        try {
            final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            Enumeration<URL> urls = (classLoader != null) ? classLoader.getResources(path)
                    : ClassLoader.getSystemResources(path);
            Properties properties = new Properties();
            while (urls.hasMoreElements()) {
                properties.putAll(PropertiesLoaderUtils.loadProperties(new UrlResource(urls.nextElement())));
            }
            properties.forEach((k, v) -> CONFIG_CONDITIONS.put((String) k, Arrays.asList(String.valueOf(v).split(","))));
        } catch (IOException ex) {
            throw new IllegalArgumentException("Unable to load @ConditionalOnClass location [" + path + "]", ex);
        }
    }

}
