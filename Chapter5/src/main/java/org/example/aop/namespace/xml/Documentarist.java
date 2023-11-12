package org.example.aop.namespace.xml;

public class Documentarist {
    protected Guitarist guitarist;

    public Documentarist(Guitarist guitarist) {
        this.guitarist = guitarist;
    }

    public Documentarist() {
    }

    public void execute() {
        guitarist.sing();
        guitarist.talk();
    }

    public void setGuitarist(Guitarist guitarist) {
        this.guitarist = guitarist;
    }
}
