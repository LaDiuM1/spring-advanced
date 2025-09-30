package study.spring_advanced.proxy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.spring_advanced.proxy.config.proxy.v1.OrderControllerV1Proxy;
import study.spring_advanced.proxy.config.proxy.v1.OrderRepositoryV1Proxy;
import study.spring_advanced.proxy.config.proxy.v1.OrderServiceV1Proxy;
import study.spring_advanced.proxy.app.v1.OrderControllerV1;
import study.spring_advanced.proxy.app.v1.OrderRepositoryV1;
import study.spring_advanced.proxy.app.v1.OrderServiceV1;
import study.spring_advanced.proxy.trace.logtrace.ThreadLocalLogTrace;
import study.spring_advanced.proxy.trace.strategy.StrategyLogTrace;

@Configuration
public class AppV1Config {

    @Bean
    public OrderControllerV1 orderControllerV1() {
        OrderControllerV1 orderControllerV1 = new OrderControllerV1(orderServiceV1());
        return new OrderControllerV1Proxy(orderControllerV1, strategyLogTraceV1());
    }

    @Bean
    public OrderServiceV1 orderServiceV1() {
        OrderServiceV1 orderServiceV1 = new OrderServiceV1(orderRepositoryV1());
        return new OrderServiceV1Proxy(orderServiceV1, strategyLogTraceV1());
    }

    @Bean
    public OrderRepositoryV1 orderRepositoryV1() {
        OrderRepositoryV1 orderRepositoryV1 = new OrderRepositoryV1();
        return new OrderRepositoryV1Proxy(orderRepositoryV1, strategyLogTraceV1());
    }

    @Bean
    public StrategyLogTrace strategyLogTraceV1() {
        return new StrategyLogTrace(new ThreadLocalLogTrace());
    }

}
