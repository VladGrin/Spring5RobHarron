package org.example.app.context.lookup.annot;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.util.StopWatch;

public class LookupDemo {
    public static void main(String[] args) {

        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(LookupConfig.class);

        AnnotDemoBean abstractBean = ctx.getBean("abstractLookupBean", AnnotDemoBean.class);
        AnnotDemoBean standardBean = ctx.getBean("standardLookupBean", AnnotDemoBean.class);
        displayInfo("abstractLookupBean", abstractBean);
        displayInfo("standardLookupBean", standardBean);
        ctx.close();
    }

    private static void displayInfo(String beanName, AnnotDemoBean bean) {
        Singer singer1 = bean.getMySinger();
        Singer singer2 = bean.getMySinger();
        System.out.println("" + beanName + ": " + "Singer Instances the Same? " + (singer1 == singer2));
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("lookupDemo");
        for (int i = 0; i < 100000; i++) {
            Singer singer = bean.getMySinger();
            singer.sing();
        }
        stopWatch.stop();
        System.out.println("1OOOOO gets took " + stopWatch.getTotalTimeMillis() + "ms");
    }
}
