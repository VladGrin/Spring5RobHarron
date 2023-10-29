package org.example.app.context.depend.primary.annot;

import org.springframework.stereotype.Component;

@Component("gopher")
public class Guitar {

    public void sing() {
        System.out.println("Cm Еb Fm Аb Вb");
    }
}
