package org.example.app.context.collections.util.xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Service("utilInjectCollection")
public class UtilCollectionInjection {

    @Autowired
    @Qualifier("map")
//    @Resource(name = "map")
    private Map<String, Object> map;

    @Autowired
    @Qualifier("props")
//    @Resource(name = "props")
    private Properties props;

//    @Autowired
//    @Qualifier("set")
    @Resource(name = "set")
    private Set set;

//    @Autowired
//    @Qualifier("list")
    @Resource(name = "list")
    private List list;

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:./spring/app-context-annotation.xml");
        ctx.refresh();
        UtilCollectionInjection instance = (UtilCollectionInjection) ctx.getBean("utilInjectCollection");
        instance.displayInfo();
        ctx.close();
    }

    public void displayInfo() {
        System.out.println("Map contents:\n ");
        map.forEach((key, value) -> System.out.println("Key: " + key + " - Value: " + value));

        System.out.println("\nProperties contents:\n");
        props.forEach((key, value) -> System.out.println("Key: " + key + " - Value: " + value));

        System.out.println("\nSet contents:\n");
        set.forEach(obj -> System.out.println("Value: " + obj));

        System.out.println("\nList contents:\n");
        list.forEach(obj -> System.out.println("Value: " + obj));
    }
}
