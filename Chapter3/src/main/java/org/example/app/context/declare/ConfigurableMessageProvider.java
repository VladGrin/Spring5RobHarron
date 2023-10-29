package org.example.app.context.declare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("provider")
public class ConfigurableMessageProvider implements MessageProvider {

    private final String message;

//    @Autowired
//    public ConfigurableMessageProvider(@Value("Configurable message") String message) {
//        this.message = message;
//    }

    @Autowired
    public ConfigurableMessageProvider(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }
}
