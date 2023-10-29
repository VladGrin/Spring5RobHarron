package org.example.app.context.lookup.xml;

public abstract class AbstractLookupDemoBean implements DemoBean {

    public abstract Singer getMySinger();

    @Override
    public void doSomething() {
        Singer mySinger = getMySinger();
        mySinger.sing();
    }
}
