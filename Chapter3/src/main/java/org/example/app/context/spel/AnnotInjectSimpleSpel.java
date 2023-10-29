package org.example.app.context.spel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("annotInjectSimpleSpel")
public class AnnotInjectSimpleSpel {

    @Value("#{injectSimpleConfig.name}")
    private String name;

    @Value("#{injectSimpleConfig.age + 1}")
    private int age;

    @Value("#{injectSimpleConfig.height}")
    private float height;

    @Value("#{injectSimpleConfig.programmer}")
    private boolean programmer;

    @Value("#{injectSimpleConfig.ageInSeconds}")
    private Long ageInSeconds;

    @Override
    public String toString() {
        return "AnnotInjectSimpleSpel{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", programmer=" + programmer +
                ", ageInSeconds=" + ageInSeconds +
                '}';
    }
}
