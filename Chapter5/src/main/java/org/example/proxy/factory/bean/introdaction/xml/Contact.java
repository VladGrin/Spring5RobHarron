package org.example.proxy.factory.bean.introdaction.xml;

public class Contact {

    private String name;
    private String phoneNumЬer;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumЬer() {
        return phoneNumЬer;
    }

    public void setPhoneNumЬer(String phoneNumЬer) {
        this.phoneNumЬer = phoneNumЬer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phoneNumЬer='" + phoneNumЬer + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
