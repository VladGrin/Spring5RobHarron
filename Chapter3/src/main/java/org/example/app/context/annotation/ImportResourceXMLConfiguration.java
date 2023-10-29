package org.example.app.context.annotation;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@ImportResource(locations = {"classpath:./spring/app-context-xml.xml"})
@Configuration
public class ImportResourceXMLConfiguration {
}
