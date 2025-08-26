package study.spring_advanced.proxy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.spring_advanced.proxy.app.proxy.v0.OrderControllerV0Proxy;
import study.spring_advanced.proxy.app.proxy.v0.OrderRepositoryV0Proxy;
import study.spring_advanced.proxy.app.proxy.v0.OrderServiceV0Proxy;
import study.spring_advanced.proxy.app.v0.*;
import study.spring_advanced.proxy.trace.logtrace.ThreadLocalLogTrace;
import study.spring_advanced.proxy.trace.strategy.StrategyLogTrace;

@Configuration
public class AppV0Config {

    @Bean
    public OrderControllerV0 orderControllerV0() {
        OrderControllerV0Impl orderController = new OrderControllerV0Impl(orderServiceV0());

        return new OrderControllerV0Proxy(orderController, strategyLogTrace());
    }

    @Bean
    public OrderServiceV0 orderServiceV0() {
        OrderServiceV0Impl orderService = new OrderServiceV0Impl(orderRepositoryV0());
        return new OrderServiceV0Proxy(orderService, strategyLogTrace());
    }

    @Bean
    public OrderRepositoryV0 orderRepositoryV0() {
        OrderRepositoryV0Impl orderRepository = new OrderRepositoryV0Impl();
        return new OrderRepositoryV0Proxy(orderRepository, strategyLogTrace());
    }

    @Bean
    public StrategyLogTrace strategyLogTrace() {
        return new StrategyLogTrace(new ThreadLocalLogTrace());
    }

}
