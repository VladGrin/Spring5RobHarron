package org.example.groovy;

import lombok.extern.slf4j.Slf4j;

import javax.script.ScriptEngineManager;

@Slf4j
public class ListScriptEngines {

    public static void main(String[] args) {
        ScriptEngineManager mgr = new ScriptEngineManager();
        mgr.getEngineFactories().forEach(factory -> {
            String engineName = factory.getEngineName();
            String languageName = factory.getLanguageName();
            String version = factory.getLanguageVersion();
            log.info("Engine name: " + engineName +
                    " Language: " + languageName +
                    "Version: " + version);
        });
    }
}
