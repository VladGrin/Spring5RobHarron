package org.example.general.configuration.annotated;

public interface MessageRenderer {

    void render () ;
    void setMessageProvider(MessageProvider provider);
    MessageProvider getMessageProvider();
}
