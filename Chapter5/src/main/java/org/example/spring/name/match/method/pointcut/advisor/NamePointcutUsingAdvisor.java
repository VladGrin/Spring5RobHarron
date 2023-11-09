package org.example.spring.name.match.method.pointcut.advisor;

import org.example.spring.namematchmeth.GrammyGuitarist;
import org.example.spring.namematchmeth.Guitar;
import org.example.spring.staticpointcat.SimpleAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;

public class NamePointcutUsingAdvisor {
    public static void main(String[] args) {
        GrammyGuitarist johnMayer = new GrammyGuitarist();
        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor(new SimpleAdvice());
        advisor.setMappedNames("sing");
        advisor.setMappedNames("rest");

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(johnMayer);
        pf.addAdvisor(advisor);
        GrammyGuitarist proxy = (GrammyGuitarist) pf.getProxy();

        proxy.sing();
        proxy.sing(new Guitar());
        proxy.rest();
        proxy.talk();
    }
}
