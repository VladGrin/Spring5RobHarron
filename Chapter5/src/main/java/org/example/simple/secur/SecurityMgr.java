package org.example.simple.secur;

public class SecurityMgr {

    private static final ThreadLocal<UserInfo> threadLocal = new ThreadLocal<>();

    public void login(String userName, String password) {
        threadLocal.set(new UserInfo(userName, password));
    }

    public void logout() {
        threadLocal.remove();
    }

    public UserInfo getLoggedOnUser() {
        return threadLocal.get();
    }
}
