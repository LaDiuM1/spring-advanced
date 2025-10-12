package study.spring_advanced.proxy.config.v3;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.spring_advanced.proxy.app.v3.*;
import study.spring_advanced.proxy.trace.logtrace.LogTrace;

@Configuration
public class AppV3Config {

    @Bean
    public OrderControllerV3 orderControllerV3(LogTrace logTrace) {
        OrderControllerV3 target = new OrderControllerV3Impl(orderServiceV3(logTrace));
        return getAdvisorProxy(target, logTrace);
    }

    @Bean
    public OrderServiceV3 orderServiceV3(LogTrace logTrace) {
        OrderServiceV3 target = new OrderServiceV3Impl(orderRepositoryV3(logTrace));
        return getAdvisorProxy(target, logTrace);
    }

    @Bean
    public OrderRepositoryV3 orderRepositoryV3(LogTrace logTrace) {
        OrderRepositoryV3 target = new OrderRepositoryV3Impl();
        return getAdvisorProxy(target, logTrace);
    }

    private <T> T getAdvisorProxy(T target, LogTrace logTrace) {
        ProxyFactory proxyFactory = new ProxyFactory(target);

        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new LogTraceAdvice(logTrace));
        proxyFactory.addAdvisor(advisor);

        return (T) proxyFactory.getProxy();
    }

}
