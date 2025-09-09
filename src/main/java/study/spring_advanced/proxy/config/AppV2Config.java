package study.spring_advanced.proxy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.spring_advanced.proxy.app.proxy.v2.handler.LogTraceInvocationHandler;
import study.spring_advanced.proxy.app.v2.*;
import study.spring_advanced.proxy.trace.logtrace.LogTrace;
import study.spring_advanced.proxy.trace.logtrace.ThreadLocalLogTrace;

import java.lang.reflect.Proxy;

@Configuration
public class AppV2Config {

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }

    @Bean
    public OrderControllerV2 orderControllerV2() {
        OrderControllerV2 orderControllerV2 = new OrderControllerImplV2(orderServiceV2());

        return getProxyInstance(orderControllerV2, new Class[]{OrderControllerV2.class});
    }

    @Bean
    public OrderServiceV2 orderServiceV2() {
        OrderServiceV2 orderServiceV2 = new OrderServiceImplV2(orderRepositoryV2());

        return getProxyInstance(orderServiceV2, new Class[]{OrderServiceV2.class});
    }

    @Bean
    public OrderRepositoryV2 orderRepositoryV2() {
        OrderRepositoryV2 orderRepositoryV2 = new OrderRepositoryImplV2();

        return getProxyInstance(orderRepositoryV2, new Class[]{OrderRepositoryV2.class});
    }

    private <T> T getProxyInstance(T target, Class<?>[] classes) {
        LogTraceInvocationHandler handler = new LogTraceInvocationHandler(target, logTrace());
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), classes, handler);
    }

}
