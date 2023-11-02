package org.example.event;

import org.springframework.context.ApplicationEvent;

public class MessageEvent extends ApplicationEvent {

    private final String msg;

    public MessageEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
