package com.drato.graduationthesis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:app.properties")
public class PropertiesUtils {

    @Autowired
    static Environment env;

    public static final String ROLE_ADMIN = "role.admin";
    public static final String FILE_UPLOAD_PATH = "file.upload-dir";

    private static AnnotationConfigApplicationContext getContext() {
        return new AnnotationConfigApplicationContext(PropertiesUtils.class);
    }

    public static Object getProperty(String name, Object defaultValue) {
        ConfigurableEnvironment env = getContext().getEnvironment();
        Object result = env.getProperty(name);
        if (result == null) result = defaultValue;
        return result;
    }
}
