package org.example;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
//        MessageRenderer renderer = new StandardOutMessageRenderer();
//        MessageProvider provider = new HelloWorldMessageProvider();

        MessageRenderer renderer = MessageSupportFactory.getInstance().getRenderer();
        MessageProvider provider = MessageSupportFactory.getInstance().getProvider();
        renderer.setMessageProvider(provider);
        renderer.render();
    }
}
