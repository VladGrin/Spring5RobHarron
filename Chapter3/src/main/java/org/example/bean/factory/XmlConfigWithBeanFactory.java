package org.example.bean.factory;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

public class XmlConfigWithBeanFactory {
    public static void main(String[] args) {

        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader rdr = new XmlBeanDefinitionReader(factory);

        rdr.loadBeanDefinitions(new ClassPathResource("spring/xml-bean-factory-config.xml"));

        Oracle oracle = (Oracle) factory.getBean("oracle");
        System.out.println(oracle.defineMeaningOfLife());

        System.out.println(factory.getBeanDefinitionCount());
        for (String name : factory.getBeanDefinitionNames()) {
            System.out.println(name);
        }

    }
}
