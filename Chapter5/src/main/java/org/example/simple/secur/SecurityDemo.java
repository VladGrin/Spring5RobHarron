package org.example.simple.secur;

import org.springframework.aop.framework.ProxyFactory;

public class SecurityDemo {
    public static void main(String[] args) {
        SecurityMgr mgr = new SecurityMgr();
        SecureBean bean = getSecureBean(mgr);
        mgr.login("John", "pwd");
        bean.writeSecureMessage();
        mgr.logout();
        try {
            mgr.login("invalid user", "pwd");
            bean.writeSecureMessage();
        } catch (SecurityException e) {
            System.out.println("Exception Caught: " + e.getMessage());
        } finally {
            mgr.logout();
        }
        try {
            bean.writeSecureMessage();
        } catch (SecurityException e) {
            System.out.println("Exception Caught: " + e.getMessage());
        }
    }

    private static SecureBean getSecureBean(SecurityMgr mgr) {
        SecureBean target = new SecureBean();
        SecurityAdvice advice = new SecurityAdvice(mgr);
        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(target);
        factory.addAdvice(advice);
        SecureBean proxy = (SecureBean) factory.getProxy();
        return proxy;
    }
}
