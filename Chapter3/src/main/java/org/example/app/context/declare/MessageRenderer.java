package org.example.app.context.declare;

public interface MessageRenderer {

    void render();

    void setMessageProvider(MessageProvider provider);

    MessageProvider getMessageProvider();
}
