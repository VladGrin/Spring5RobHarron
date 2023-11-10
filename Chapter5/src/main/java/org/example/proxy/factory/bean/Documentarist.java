package org.example.proxy.factory.bean;

public class Documentarist {
    private Guitarist guitarist;

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
