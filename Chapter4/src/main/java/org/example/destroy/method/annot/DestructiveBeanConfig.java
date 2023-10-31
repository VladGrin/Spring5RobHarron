package org.example.destroy.method.annot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class DestructiveBeanConfig {

    @Lazy
    @Bean(initMethod = "afterPropertiesSet", destroyMethod = "destroy")
    DestructiveBeanWithJSR250 destructiveBean() {
        DestructiveBeanWithJSR250 destructiveBean = new DestructiveBeanWithJSR250();
        destructiveBean.setFilePath(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "test.txt");
        return destructiveBean;
    }
}
