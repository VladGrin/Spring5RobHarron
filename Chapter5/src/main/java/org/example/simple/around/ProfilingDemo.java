package org.example.simple.around;

import org.springframework.aop.framework.ProxyFactory;

public class ProfilingDemo {
    public static void main(String[] args) {
        WorkerBean bean = getWorkerBean();
        bean.doSomeWork(20000000);
    }

    private static WorkerBean getWorkerBean() {
        WorkerBean bean = new WorkerBean();

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(bean);
        factory.addAdvice(new ProfilingInterceptor());

        return (WorkerBean) factory.getProxy();
    }
}
