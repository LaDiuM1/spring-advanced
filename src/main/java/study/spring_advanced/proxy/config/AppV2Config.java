package study.spring_advanced.proxy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.spring_advanced.proxy.config.proxy.v2.handler.LogTraceInvocationHandler;
import study.spring_advanced.proxy.app.v2.*;
import study.spring_advanced.proxy.trace.logtrace.LogTrace;

import java.lang.reflect.Proxy;

@Configuration
public class AppV2Config {

    private final String[] loggingPatterns = {"request*", "order*", "save*"};

    @Bean
    public OrderControllerV2 orderControllerV2(LogTrace logTrace) {
        OrderControllerV2 orderControllerV2 = new OrderControllerImplV2(orderServiceV2(logTrace));

        return getProxyInstance(orderControllerV2, new Class[]{OrderControllerV2.class}, logTrace);
    }

    @Bean
    public OrderServiceV2 orderServiceV2(LogTrace logTrace) {
        OrderServiceV2 orderServiceV2 = new OrderServiceImplV2(orderRepositoryV2(logTrace));

        return getProxyInstance(orderServiceV2, new Class[]{OrderServiceV2.class}, logTrace);
    }

    @Bean
    public OrderRepositoryV2 orderRepositoryV2(LogTrace logTrace) {
        OrderRepositoryV2 orderRepositoryV2 = new OrderRepositoryImplV2();

        return getProxyInstance(orderRepositoryV2, new Class[]{OrderRepositoryV2.class}, logTrace);
    }

    private <T> T getProxyInstance(T target, Class<?>[] classes, LogTrace logTrace) {
        LogTraceInvocationHandler handler = new LogTraceInvocationHandler(target, logTrace, loggingPatterns);
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), classes, handler);
    }

}
