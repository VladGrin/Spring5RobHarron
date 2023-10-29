package org.example;

import java.util.Properties;

public class MessageSupportFactory {

    private static MessageSupportFactory instance;

    private Properties props;
    private MessageProvider provider;
    private MessageRenderer renderer;

    public MessageSupportFactory() {
        props = new Properties();
        try {
            props.load(this.getClass().getResourceAsStream("/msf.properties"));
            String providerClass = props.getProperty("provider.class");
            String rendererClass = props.getProperty("renderer.class");
            renderer = (MessageRenderer) Class.forName(rendererClass).newInstance();
            provider = (MessageProvider) Class.forName(providerClass).newInstance();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    static {
        instance = new MessageSupportFactory();
    }

    public static MessageSupportFactory getInstance() {
        return instance;
    }

    public MessageProvider getProvider() {
        return provider;
    }

    public MessageRenderer getRenderer() {
        return renderer;
    }
}
