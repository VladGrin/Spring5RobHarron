package org.example.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service("singerService")
public class SingerServiceImpl implements SingerService {

    @Autowired
    ApplicationContext ctx;

    @Autowired(required = false)
    private RuleFactory ruleFactory;

    @Autowired
    private RuleEngine ruleEngine;

    public void applyRule(Singer singer) {
        Rule ageCategoryRule = ruleFactory.getAgeCategoryRule();
        ruleEngine.run(ageCategoryRule, singer);
    }
}