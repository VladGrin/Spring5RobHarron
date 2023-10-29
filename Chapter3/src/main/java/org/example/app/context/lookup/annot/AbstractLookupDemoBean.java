package org.example.app.context.lookup.annot;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component("abstractLookupBean")
public class AbstractLookupDemoBean implements AnnotDemoBean {

    @Lookup("singer")
    public Singer getMySinger() {
        return null;
    }

    @Override
    public void doSomething() {
        Singer mySinger = getMySinger();
        mySinger.sing();
    }
}
