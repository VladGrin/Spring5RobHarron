package org.example.property.editors;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar {

    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        registry.registerCustomEditor(Date.class, new CustomDateEditor(dateFormatter, true));
        registry.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    public static void main(String... args) throws Exception {
        File file = File.createTempFile("test", "txt");
        file.deleteOnExit();
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:./spring/app-context-01.xml");
        ctx.refresh();

        PropertyEditorBean bean = (PropertyEditorBean) ctx.getBean("builtInSample");
        ctx.close();
    }
}
