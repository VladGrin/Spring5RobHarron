package org.example.app.context.depend.primary.annot;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.GenericApplicationContext;

public class TargetConfigDemo {

    @Bean
    @Primary
    public Foo fooImplOne() {
        return new FooImplOne();
    }

    @Bean
    public Foo fooImplTwo() {
        return new FooImplTwo();
    }

    @Bean
    public Bar bar() {
        return new Bar();
    }

    @Bean
    public TrickyTarget trickyTarget() {
        return new TrickyTarget();
    }

    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(TargetConfigDemo.class);
        TrickyTarget t = ctx.getBean(TrickyTarget.class);
        ctx.close();
    }
}
