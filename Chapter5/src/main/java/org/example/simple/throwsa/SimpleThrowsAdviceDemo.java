package org.example.simple.throwsa;

import org.springframework.aop.framework.ProxyFactory;

public class SimpleThrowsAdviceDemo {
    public static void main(String[] args) {
        ErrorBean bean = new ErrorBean();

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(bean);
        factory.addAdvice(new SimpleThrowsAdvice());

        ErrorBean proxy = (ErrorBean) factory.getProxy();
        try {
            proxy.errorProneMethod();
        } catch (Exception ignored) {}
        try {
            proxy.otherErrorProneMethod();
        } catch (Exception ignored) {}
    }
}
