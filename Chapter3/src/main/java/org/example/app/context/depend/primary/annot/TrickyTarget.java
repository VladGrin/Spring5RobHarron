package org.example.app.context.depend.primary.annot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class TrickyTarget {
    private Foo fooOne;
    private Foo fooTwo;
    private Bar bar;

    public TrickyTarget() {
    }

    public TrickyTarget(Foo foo) {
        System.out.println("Target(Foo) called");
    }

    public TrickyTarget(Foo foo, Bar bar) {
        System.out.println("Target(Foo, Bar) called");
    }

    @Autowired
//    @Qualifier("fooImplOne")
    public void setFooOne(Foo fooOne) {
        this.fooOne = fooOne;
        System.out.println("Property fooOne set");
    }

    @Autowired
//    @Qualifier("fooImplTwo")
    public void setFooTwo(Foo foo) {
        this.fooTwo = foo;
        System.out.println("Property fooTwo set");
    }

    @Autowired
    public void setBar(Bar bar) {
        this.bar = bar;
        System.out.println("Property bar set");
    }
}
