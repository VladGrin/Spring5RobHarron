package org.example.app.context.lookup.annot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"org.example.app.context.lookup.annot"})
public class LookupConfig {
}
