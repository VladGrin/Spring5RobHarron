package org.example.app.context.lookup.annot;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("singer")
@Scope("prototype")
public class Singer {
    private String lyric = "I played а quick game of chess with the salt and pepper shaker";

    public void sing() {
        // закомментировано, поскольку оскверняет
        // вывод на консоль
//         System.out.println(lyric);
    }
}
