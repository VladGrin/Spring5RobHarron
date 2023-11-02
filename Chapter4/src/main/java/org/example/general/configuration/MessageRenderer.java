package org.example.general.configuration;

public interface MessageRenderer {

    void render () ;
    void setMessageProvider(MessageProvider provider);
    MessageProvider getMessageProvider();
}
