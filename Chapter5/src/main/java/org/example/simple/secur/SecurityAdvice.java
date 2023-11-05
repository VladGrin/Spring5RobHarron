package org.example.simple.secur;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class SecurityAdvice implements MethodBeforeAdvice {

    private final SecurityMgr mgr;

    public SecurityAdvice(SecurityMgr mgr) {
        this.mgr = mgr;
    }


    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        UserInfo user = mgr.getLoggedOnUser();

        if (user == null) {
            System.out.println("No user authenticated");
            throw new SecurityException("You must login before attempting to invoke the method: " + method.getName());
        } else if ("John".equals(user.getUserName())) {
            System.out.println("Logged in user is John - ОКАУ!");
        } else {
            System.out.println("Logged in user is " + user.getUserName() + " NOT GOOD : (");
            throw new SecurityException("User " + user.getUserName() + " is not allowed"
                    + "access to method" + method.getName());
        }
    }
}
