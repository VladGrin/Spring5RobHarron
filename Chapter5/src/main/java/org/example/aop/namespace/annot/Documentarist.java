package org.example.aop.namespace.annot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Documentarist {
    protected Guitarist guitarist;

    public void execute() {
        guitarist.sing();
        guitarist.talk();
    }

    public void setGuitarist(Guitarist guitarist) {
        this.guitarist = guitarist;
    }
}
