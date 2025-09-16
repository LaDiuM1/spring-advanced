package study.spring_advanced.proxy.config;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.spring_advanced.proxy.app.proxy.v3.LogTraceAdvice;
import study.spring_advanced.proxy.app.v3.*;
import study.spring_advanced.proxy.trace.logtrace.LogTrace;
import study.spring_advanced.proxy.trace.logtrace.ThreadLocalLogTrace;

@Configuration
public class AppV3Config {

    @Bean
    public LogTrace logTraceV3() {
        return new ThreadLocalLogTrace();
    }

    @Bean
    public OrderControllerV3 orderControllerV3() {
        OrderControllerV3 target = new OrderControllerV3Impl(orderServiceV3());
        return getAdvisorProxy(target);
    }

    @Bean
    public OrderServiceV3 orderServiceV3() {
        OrderServiceV3 target = new OrderServiceV3Impl(orderRepositoryV3());
        return getAdvisorProxy(target);
    }

    @Bean
    public OrderRepositoryV3 orderRepositoryV3() {
        OrderRepositoryV3 target = new OrderRepositoryV3Impl();
        return getAdvisorProxy(target);
    }

    private <T> T getAdvisorProxy(T target) {
        ProxyFactory proxyFactory = new ProxyFactory(target);

        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new LogTraceAdvice(logTraceV3()));
        proxyFactory.addAdvisor(advisor);

        return (T) proxyFactory.getProxy();
    }

}
