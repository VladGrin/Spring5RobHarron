package org.example.app.context.declare;

//@Component("provider")
public class HelloWorldMessageProvider implements MessageProvider {

    public String getMessage() {
        return "Application context in action.";
    }
}
